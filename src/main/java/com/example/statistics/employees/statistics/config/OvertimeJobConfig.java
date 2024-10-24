package com.example.statistics.employees.statistics.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class OvertimeJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public OvertimeJobConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Job overtimeJob(Step overtimeStep) {
        return new JobBuilder("overtimeJob", jobRepository)
                .start(overtimeStep)
                .build();
    }

    @Bean
    public Step overtimeStep() {
        return new StepBuilder("overtimeStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    // Logic for calculating overtime
                    System.out.println("Calculating overtime...");
                    return null;
                }, transactionManager)
                .build();
    }
}
