package org.helicalmoment.common.testing;

import java.util.List;

public interface TestCase<T extends TestParameters> {
	String getName();
	String getDescription();
	boolean isIgnored();
	List<TestStep<T>> getSteps();
	void preTest();
	void postTest();
	void executeTest();
	T getTestParameters();
}
