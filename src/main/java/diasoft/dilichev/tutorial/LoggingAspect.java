package diasoft.dilichev.tutorial;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("@annotation(diasoft.dilichev.tutorial.LogRequest)")
    public void loggableMethod() {}

    @AfterReturning(value = "loggableMethod()", returning = "result")
    public void logMethod(JoinPoint jp, Object result) {
        String methodName = jp.getSignature().getName();
        logger.info("Executing method: " + methodName + "\n");
        logger.info("Method arguments: " );
        for(Object arg: jp.getArgs()) {
            logger.info(arg.toString() + " ");
        }
        logger.info( "\n");

        if(methodName == "primes")
        {
            logger.info("Method returned Primes object: \n{\n  id: \n    " + ((Primes) result).getId()
                    + ",\n  primes:\n    [\n      ");
            for(int prime: ((Primes) result).getPrimes())
            {
                logger.info(prime + ", ");
            }
            logger.info("\n    ]\n}\n");
        }
        else if(methodName == "greeting")
        {
            logger.info("Method returned Greeting object: \n{\n  id: \n    "
                    + ((Greeting) result).getId() + ",\n  content:\n    "
                    + ((Greeting) result).getContent() + "\n}\n");
        }
    }
}
