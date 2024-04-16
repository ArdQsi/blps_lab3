package com.webapp.scheduler;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;

import com.webapp.config.AutoWiringSpringBeanJobFactory;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import java.util.Properties;

@Configuration
public class QrtzScheduler {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        logger.debug("Configuring Job factory");
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public Scheduler scheduler(Trigger trigger, JobDetail job, SchedulerFactoryBean factory) throws SchedulerException {
        logger.debug("Getting a handle to the Scheduler");
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(job, trigger);
        logger.debug("Starting Scheduler threads");
        scheduler.start();
        return scheduler;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(springBeanJobFactory());
        factory.setQuartzProperties(quartzProperties());
        return factory;
    }

    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean
    public JobDetail jobDetail() {
        return newJob().ofType(AutomaticDebitingOfMoneyJob.class).storeDurably().withIdentity("job_detail").build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {
        logger.info("Configuring trigger to fire every day in 5 am");

        return newTrigger().forJob(job).withIdentity(TriggerKey.triggerKey("Qrtz_Trigger")).withDescription("Debiting of money trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 5s * * ?"))
                .build();
    }
}