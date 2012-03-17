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
package org.helicalmoment.common.testing.junit;

import java.util.List;

import org.helicalmoment.common.testing.TestCase;

/**
 * Defines the strategy to use to retrieve the list of {@link TestCase}s for use
 * with a {@link NamedParameterized} test.
 */
public interface ParametersStrategy {
	/**
	 * Get the path to the input file to load the test cases from.
	 * <p/>
	 * TODO: Consider moving this to a child interface called
	 * {@code FileBasedParametersStrategy} to allow for loading test cases from
	 * non-file sources (e.g., a database).
	 * 
	 * @param path
	 *            the path to the file which contains the test cases
	 */
	void setPath(String path);

	/**
	 * Get the test cases that should be executed for this test.
	 * 
	 * @return the list of test cases
	 * @throws Throwable
	 *             if an error occurs
	 */
	List<? extends TestCase<?>> getTestCases() throws Throwable;
}
