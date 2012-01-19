package org.helicalmoment.common.test.junit.runners;

import java.util.List;

import org.helicalmoment.common.test.TestCase;

public interface ParametersStrategy {
	void setPath(String path);
	List<? extends TestCase> getTestCases() throws Throwable;
}
