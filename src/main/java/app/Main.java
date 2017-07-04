package app;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;


public class Main {
    static {
        PropertyConfigurator.configure(Main.class.getResource("/log4j.properties"));
    }

    public static void main(String[] args) throws Exception {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
        Job job = context.getBean(Job.class);
        JobLauncher launcher = context.getBean(JobLauncher.class);

        final Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
        parameters.put("input-file", new JobParameter("classpath:input/library.zip"));
        launcher.run(job, new JobParameters(parameters));

        context.close();
    }
}
