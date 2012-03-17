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

/**
 * Describes the actions to take during a single step within a {@link TestCase}.
 * <p/>
 * Users should generally extend {@link AbstractTestStep} rather than implement
 * this interface directly since it provides default implementations for the
 * {@link TestStep#getName()} and {@link TestStep#getDescription()} getters.
 * 
 * @param <T>
 *            the implementation of {@link TestParameters} which will be used to
 *            provide this step its {@link TestCase} data
 */
public interface TestStep<T extends TestParameters> {
	/**
	 * Get the name of this step. Displayed on the debug output of the test.
	 * 
	 * @return the name of the step
	 */
	String getName();

	/**
	 * Get the description of this step. Displayed on the debug output of the
	 * test.
	 * 
	 * @return the description of the step
	 */
	String getDescription();

	/**
	 * Execute the test step's actions and (optionally) assertions.
	 * 
	 * @param parms
	 *            the {@link TestParameters} required by the step
	 */
	void execute(T parms);
}
