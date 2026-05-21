package com.hardik.problemsservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class LocalExecutorService {

    private static final Logger logger = LoggerFactory.getLogger(LocalExecutorService.class);

    public String executeJava(){
        Path tempFile = null;
        try{
            Resource classPathResource = new ClassPathResource("BinarySearch.java");
            tempFile = Files.createTempFile("run-", ".java");
            Files.copy(classPathResource.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

            ProcessBuilder pb = new ProcessBuilder("java",  tempFile.toString());
            pb.redirectErrorStream(true);
            Process process = pb.start();

            String output = new String(process.getInputStream().readAllBytes());

            process.waitFor();

            return output;
        }
        catch(Exception e){
            return "Execution Failed : \n" + e.getMessage();
        }
        finally {
            if (tempFile != null) {
                boolean deleted = tempFile.toFile().delete();
                logger.info("Deleted temp file {}: {}", tempFile, deleted);
            }
        }

    }
}
