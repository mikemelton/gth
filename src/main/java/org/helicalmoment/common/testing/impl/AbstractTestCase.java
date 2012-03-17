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
package org.helicalmoment.common.testing.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import org.helicalmoment.common.testing.TestCase;
import org.helicalmoment.common.testing.TestParameters;
import org.helicalmoment.common.testing.TestStep;

/**
 * A default implementation of {@link TestCase} which provides an implementation
 * for {@link TestCase#executeTest()}. Users should generally extend from this
 * class rather than implementing {@code TestCase} directly, as this
 * implementation executes the test case's {@link TestStep}s and provides empty
 * implementations of {@link TestCase#preTest()} and {@link TestCase#postTest()}
 * . The only unimplemented method is {@link TestCase#getTestParameters()}.
 * 
 * @param <T>
 *            the {@link TestParameters} implementation that this test case
 *            exposes
 */
@AllArgsConstructor @RequiredArgsConstructor @Slf4j @Getter @ToString
public abstract class AbstractTestCase<T extends TestParameters> implements TestCase<T> {
	@NonNull private final String name;
	@NonNull private final String description;
	private final boolean ignored;
	@Setter(AccessLevel.PROTECTED) private List<TestStep<T>> steps;

	/**
	 * {@inheritDoc}
	 */
	public void preTest() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void postTest() {
	}

	/**
	 * {@inheritDoc}
	 */
	public abstract T getTestParameters();

	/**
	 * Adds a {@link TestStep} to the list of steps executed by this test case.
	 * 
	 * @param step
	 *            the {@code TestStep} to add
	 */
	public void addStep(TestStep<T> step) {
		if (null == steps)
			steps = new ArrayList<TestStep<T>>();
		steps.add(step);
	}

	/**
	 * {@inheritDoc}
	 */
	public void executeTest() {
		boolean completed = false;
		try {
			log.info("------------------------- STARTING TEST [{}] -------------------------", name);
			log.info("Scenario description: [{}]", description);
			preTest();
			if (null != steps) {
				log.info("Expected number of steps: [{}]", steps.size());
				for (TestStep<T> step : steps) {
					log.info("Executing step [{}]", step);
					step.execute(getTestParameters());
				}
			} else {
				log.info("No steps defined for test case");
			}
			postTest();
			completed = true;
		} finally {
			String result = completed ? "COMPLETED TEST" : "TEST FAILED";
			log.info("------------------------- {} [{}] -------------------------\n", result, name);
		}
	}
}
