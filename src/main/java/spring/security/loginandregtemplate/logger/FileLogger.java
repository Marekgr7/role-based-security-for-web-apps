package spring.security.loginandregtemplate.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Aspect
@Component
public class FileLogger {
    private Logger logger = Logger.getLogger("CustomFileLogger");

    public FileLogger() {
        try {
            //TODO LOGGER FILE NAME and location
            FileHandler fileHandler = new FileHandler("Logger.log", true);

            logger.addHandler(fileHandler);
            fileHandler.setFormatter(new SimpleFormatter());

            logger.info("Logger initialization successful\n");

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    /* Logging before method execution */
    @Before("execution(* spring..*(..))")
    public void loggerBeforeAll(JoinPoint joinPoint) {
        logger.info("BEFORE - Method " + joinPoint.getSignature() + "\n\t\t\t" + "calling with args: "
                + Arrays.toString(joinPoint.getArgs()));
    }

    /* Logging after successful method execution */
    @AfterReturning(pointcut = "execution(* spring..*(..))", returning = "result")
    public void loggerAfterUser(JoinPoint joinPoint, Object result) {
        if(result != null) {
            logger.info("AFTER - Method" + joinPoint.getSignature() + "\n\t\t\t" + "successfully returned Object: " + result);
        } else {
            logger.info("AFTER - Method" + joinPoint.getSignature() + "\n\t\t\t" + "successfully ended");
        }
    }

    /* Logging after throwing error */
    @AfterThrowing(pointcut = "execution(* spring..*(..))", throwing = "exception")
    public void loggerAfterException(JoinPoint joinPoint, Exception exception) {
        logger.warning("Exception: " + exception + "- Method" + joinPoint.getSignature() +
                (Arrays.asList(exception.getStackTrace())));
    }
}
