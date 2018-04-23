package com.softserve.edu.test;

import com.softserve.edu.test.helpers.Listener;
import data.applications.ApplicationSourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.internal.thread.ThreadTimeoutException;
import pages.Application;

@Listeners(Listener.class)

public class BaseTest {
    public static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeClass
    public void setupClass() {
        Application.get(ApplicationSourceRepository.cliOption());
        logger.info("setup class");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Application.remove();
        logger.info("Driver was destroyed. Browser closed");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResult) {
        String methodName = testResult.getMethod().getMethodName();
        boolean testPassed = testResult.getStatus() == ITestResult.SUCCESS;
        String msg = "\n";

        if(testPassed) {
            logger.info(methodName + " - test passed");
        } else {
            logger.error(methodName + " - test failed");
            Throwable throwable = testResult.getThrowable();
            if (throwable != null) {
                msg += "\t" + throwable.toString();
                if (!(throwable instanceof ThreadTimeoutException)) {
                    for (StackTraceElement e : throwable.getStackTrace()) {
                        msg = msg + "\n        at " + e.toString();
                    }
                }
            }
            logger.error(msg);
        }
    }
}
