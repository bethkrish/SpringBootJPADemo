package com.vijayforvictory.demo.jpa;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vijay
 *
 */
@Aspect
@Component
public class LoggingAdvice {

	public LoggingAdvice() {

	}

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAdvice.class);

	/**
	 * 
	 */
	@Pointcut(value = "execution(* com.vijayforvictory.demo.jpa.*.*.*(..) )")
	public void myPointcut() {
	}

	/**
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("myPointcut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {

		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
		Object[] array = pjp.getArgs();

		// Commented this to get clear logs. Uncomment this to get detailed AOP logging
		if (className.equals("")) {

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("BEFORE : " + className + " : " + methodName + "()" + "arguments : "
						+ mapper.writeValueAsString(array));
			}
		}

		Object object = pjp.proceed();

		// Commented this to get clear logs. Uncomment this to get detailed AOP logging
		if (className.equals("")) {

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("AFTER : " + className + " : " + methodName + "()" + "Response : "
						+ mapper.writeValueAsString(object));

			}
		}
		return object;
	}

	/**
	 * @param jp
	 * @return
	 */
	@Before(value = "execution(* com.vijayforvictory.demo.jpa.controller.*.*(..) )")
	public Object appLoggerBefore(JoinPoint jp) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(jp.getSignature().toLongString());
		}
		return jp.getClass();
	}

	/**
	 * @param joinPoint
	 * @throws Throwable
	 */
//	@Around("@annotation(com.vijayforvictory.demo.jpa.TrackTime)")
	@Around("callAt(trackTime)")
	public Object around(ProceedingJoinPoint joinPoint, TrackTime trackTime) throws Throwable {
		long startTime = System.currentTimeMillis();

		Object object = joinPoint.proceed();

		long timeTaken = System.currentTimeMillis() - startTime;
		LOGGER.info("Time Taken by {} is {} milliseconds", trackTime.value(), timeTaken);
		return object;
	}

	@Pointcut("@annotation(trackTime)")
	public void callAt(TrackTime trackTime) {
	}

}
