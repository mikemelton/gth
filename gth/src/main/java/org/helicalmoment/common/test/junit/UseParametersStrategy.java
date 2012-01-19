package org.helicalmoment.common.test.junit.runners;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UseParametersStrategy {
	Class<? extends ParametersStrategy> clazz();
	String path();
}
