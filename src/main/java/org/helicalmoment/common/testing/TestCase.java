/*
 * Copyright 2012 Mike Melton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.helicalmoment.common.testing;

import java.util.List;

/**
 * A generic test case for use with the {@link NamedParameterized} JUnit test
 * runner. Test cases have names and descriptions; when test cases are running
 * in Eclipse's JUnit UI, you can quickly see which test case is running, and,
 * more importantly, which test cases have failed. Contrast this with
 * {@link org.junit.runners.Parameterized}, which simply displays the test
 * method's name and a subscript.
 * <p/>
 * A test case defines at least one step that it executes. Steps are executed in
 * the order they are defined in the list.
 * <p/>
 * Implementations should generally extend {@link DefaultTestCase} rather than
 * implementing this interface directly. {@code DefaultTestCase} takes care of
 * executing the test steps.
 * 
 * @param <T>
 *            Each test case must declare which implementation of
 *            {@link TestParameters} it uses. Steps declare a matching
 *            implementation, which allows a test case to provide data to a
 *            step, without the step having to know how to access that data
 *            within the test case.
 */
public interface TestCase<T extends TestParameters> {
	/**
	 * Get the name of the test case. This is the value that is displayed in the
	 * test runner during execution of a test.
	 * 
	 * @return the name of the test case
	 */
	String getName();

	/**
	 * Get the description of the test case. This is displayed in the log output
	 * while the tests are running.
	 * 
	 * @return the description of the test case
	 */
	String getDescription();

	/**
	 * Get whether the test should be ignored during test execution. Allows the
	 * user to selectively ignore individual test cases without having to ignore
	 * the entire test file.
	 * 
	 * @return {@code true} if the test case should be ignored; {@code false}
	 *         otherwise (default)
	 */
	boolean isIgnored();

	/**
	 * Get the list of steps associated with this test case. Steps are executed
	 * in the order they are defined in this list.
	 * 
	 * @return the list of steps to execute
	 */
	List<TestStep<T>> getSteps();

	/**
	 * Executed prior to {@link TestCase#executeTest()}. Can be used for test
	 * initialization.
	 */
	void preTest();

	/**
	 * Executed after {@link TestCase#executeTest()}. Can be used for test
	 * cleanup.
	 */
	void postTest();

	/**
	 * Run the test.
	 */
	void executeTest();

	/**
	 * Returns a reference to the {@link TestParameters} implementation for this
	 * test case. The parameters class allows steps to indirectly access data
	 * from the test case without needing a reference to the test case itself.
	 * 
	 * @return the test parameters
	 */
	T getTestParameters();
}
