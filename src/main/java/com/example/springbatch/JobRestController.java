package com.example.springbatch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class JobRestController {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @GetMapping("/startJob")
    public BatchStatus load() throws Exception{
        Map<String, JobParameter> parameters = new HashMap<>();
        parameters.put("time",new JobParameter(System.currentTimeMillis()));
        JobParameters param = new JobParameters(parameters);
        JobExecution jobExecution=jobLauncher.run(job,param);

        while(jobExecution.isRunning()) {
            System.out.println("le job est entrein de s'executer...");
        }
        return jobExecution.getStatus();

    }



}
