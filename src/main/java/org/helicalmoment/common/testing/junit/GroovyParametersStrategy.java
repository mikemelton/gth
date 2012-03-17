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

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import org.helicalmoment.common.testing.TestCase;

/**
 * A {@link ParametersStrategy} implementation which reads a Groovy file to load
 * test cases. As with all {@code ParametersStrategy} implementations, the
 * expected output is {@code List<TestCase>}.
 */
@AllArgsConstructor
public class GroovyParametersStrategy implements ParametersStrategy {
	@Setter @NonNull private String path;

	/**
	 * Gets the test cases from the Groovy file defined by {@code path}.
	 * 
	 * @throws IOException
	 *             if there is an error accessing or reading the file
	 * @throws IllegalStateException
	 *             if the Groovy script defined by the file does not return a
	 *             {@code List}
	 */
	@SuppressWarnings("unchecked")
	public List<? extends TestCase<?>> getTestCases() throws IOException {
		GroovyShell gs = new GroovyShell(new Binding());
		// TODO: Handle file location better
		Reader r = new InputStreamReader(getClass().getClassLoader().getResource(path).openStream());
		Object contents = gs.evaluate(r);
		if (!(contents instanceof List<?>))
			throw new IllegalStateException(String.format("Expecting List<TestCase>, got %s", contents.getClass()));

		// TODO: Type-check each element?

		return (List<TestCase<?>>) contents;
	}
}
