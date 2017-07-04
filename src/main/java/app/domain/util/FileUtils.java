package app.domain.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtils {
    /**
     * Unzip ZIF file.
     * @param source Resource representing ZIP file to unzip
     * @param destination Resource representing output directory
     * @throws IOException
     */
    public static void unzip(Resource source, Resource destination) throws IOException {
        final ZipInputStream zis = new ZipInputStream(source.getInputStream());
        try {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                final File file = new File(destination.getFile(), entry.getName());
                if (entry.isDirectory()) {
                    file.mkdirs();
                    continue;
                }

                final OutputStream os = new FileOutputStream(file);
                try {
                    IOUtils.copy(zis, os);
                } finally {
                    os.close();
                }
            }
        } finally {
            zis.closeEntry();
            zis.close();
        }
    }

    public static void removeRecursively(File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                removeRecursively(file);
            } else {
                file.delete();
            }
        }
        dir.delete();
    }
}
