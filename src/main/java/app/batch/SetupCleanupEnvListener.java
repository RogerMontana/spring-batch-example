package app.batch;


import app.domain.util.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

/**
 * @author Anton German
 * @since 09 June 2016
 */

public class SetupCleanupEnvListener implements JobExecutionListener {

    private static Logger logger = Logger.getLogger(SetupCleanupEnvListener.class);

    @Autowired
    private ApplicationContext applicationContext;
    private Resource workingLocation;

    public void setWorkingLocation(Resource workingLocation) {
        this.workingLocation = workingLocation;
    }

    @Override
    public void beforeJob(JobExecution execution) {
        logger.info("Setup environment ...");
        try {
            final File workingDir = workingLocation.getFile();
            if (workingDir.exists()) {
                if (!workingDir.isDirectory()) {
                    throw new IllegalStateException("Working location is not a folder: " + workingDir.getCanonicalPath());
                }
                FileUtils.removeRecursively(workingDir);
            }
            if (!workingDir.mkdirs()) {
                throw new IllegalStateException("Cannot create directories: " + workingDir.getCanonicalPath());
            }
            FileUtils.unzip(getInputResource(execution), workingLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterJob(JobExecution execution) {
        logger.info("Cleanup environment ...");
        try {
            FileUtils.removeRecursively(workingLocation.getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Resource getInputResource(JobExecution execution) {
        final String location = execution.getJobParameters().getString("input-file");
        return applicationContext.getResource(location);
    }
}

