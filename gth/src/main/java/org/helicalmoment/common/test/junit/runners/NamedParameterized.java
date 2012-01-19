package org.helicalmoment.common.test.junit.runners;

import static org.fest.reflect.core.Reflection.constructor;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.helicalmoment.common.test.TestCase;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

public class NamedParameterized extends Suite {
	private final List<Runner> runners = new ArrayList<Runner>();
	
	/**
	 * Annotation for a method which provides named parameters to be injected into the
	 * test class constructor by <code>NamedParameterized</code>
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public static @interface NamedParameters {}

	private static class TestClassRunnerForNamedParameters extends BlockJUnit4ClassRunner {
		final TestCase testCase;
		final int index;

		TestClassRunnerForNamedParameters(Class<?> clazz, TestCase testCase, int index) throws InitializationError {
			super(clazz);
			this.testCase = testCase;
			this.index = index;
		}

		@Override
		public Object createTest() throws Exception {
			return getTestClass().getOnlyConstructor().newInstance(computeParams());
		}

		private TestCase computeParams() throws Exception {
			return testCase;
		}

		@Override
		protected String getName() {
			return String.format("[%s]", index);
			
		}

		@Override
		protected String testName(final FrameworkMethod method) {
			return testCase.getName();
		}

		@Override
		protected void validateConstructor(List<Throwable> errors) {
			validateOnlyOneConstructor(errors);
		}

		@Override
		protected Statement classBlock(RunNotifier notifier) {
			return childrenInvoker(notifier);
		}
		
		@Override
		protected Annotation[] getRunnerAnnotations() {
			return new Annotation[0];
		}
	}
	
	public NamedParameterized(Class<?> clazz) throws Throwable {
		super(clazz, Collections.<Runner>emptyList());
		List<? extends TestCase> parametersList = getParametersList(getTestClass());
		for (int i = 0; i < parametersList.size(); i++)
			runners.add(new TestClassRunnerForNamedParameters(getTestClass().getJavaClass(), parametersList.get(i), i));
	}

	@Override
	protected List<Runner> getChildren() {
		return runners;
	}

	@SuppressWarnings("unchecked")
	public List<? extends TestCase> getParametersList(TestClass testClass) throws Throwable {
		// First, try to find a UseParametersStrategy annotation on the runtime class
		for (Annotation a : testClass.getAnnotations()) {
			if (a instanceof UseParametersStrategy) {
				UseParametersStrategy ups = (UseParametersStrategy)a;
				
				ParametersStrategy ps = constructor()
					.withParameterTypes(String.class)
					.in(ups.clazz())
					.newInstance(ups.path());
				
				return ps.getTestCases();
			}
		}
		
		// Failing that, look for a public static method annotated with NamedParameters
		FrameworkMethod parmMethod = getParametersMethod(testClass);
		if (null != parmMethod)
			return (List<? extends TestCase>) getParametersMethod(testClass).invokeExplosively(null);

		// If we get this far, no parameters are defined
		throw new IllegalStateException("No parameters found");
	}

	private FrameworkMethod getParametersMethod(TestClass testClass) {
		List<FrameworkMethod> methods= testClass.getAnnotatedMethods(NamedParameters.class);
		for (FrameworkMethod m : methods) {
			int modifiers= m.getMethod().getModifiers();
			if (Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers))
				return m;
		}
		
		return null;
	}
}
