package danny.musicmanager.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAdvice {


    private final Log log = LogFactory.getLog(this.getClass());

    /**
     * invoked before any service class is executed.
     *
     * @param joinPoint jointPoint
     */
    @Before(value = "execution(* userapi.employer.service.*.*(..))")
    public void serviceLogging(JoinPoint joinPoint) {
        log.info(String.format("Service Layer: %s", joinPoint.getSignature().getName()));
    }

    /**
     *
     * @param joinPoint joint point
     */
    @Before(value = "execution(* userapi.employer.controller.rest.*.*(..))")
    public void controllerLogging(JoinPoint joinPoint) {
        log.info(String.format("Rest Controller: %s", joinPoint.getSignature().getName()));
    }

    /**
     *
     * @param joinPoint joint point
     */
    @Before(value = "execution(* userapi.employer.repository.*.*(..))")
    public void repositoryLogging(JoinPoint joinPoint) {
        log.info(String.format("Repository Layer: %s", joinPoint.getSignature().getName()));
    }


}
