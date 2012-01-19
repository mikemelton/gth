package org.helicalmoment.common.test;

import java.util.List;

public interface TestCase {
	String getName();
	String getDescription();
	boolean isIgnored();
	List<TestStep> getSteps();
	void preTest();
	void executeTest();
	void postTest();
}
