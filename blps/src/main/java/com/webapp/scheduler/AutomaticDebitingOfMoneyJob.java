package com.webapp.scheduler;

import com.webapp.service.UserService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutomaticDebitingOfMoneyJob implements Job {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("Started execution of AutomaticDebitingOfMoneyJob with context = {}", context);

        userService.automaticDebitingOfMoney();

        logger.info("Finished execution of AutomaticDebitingOfMoney");
    }
}
