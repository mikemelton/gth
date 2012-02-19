/*
 * Copyright 2011 Mike Melton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
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
import lombok.Setter;

import org.helicalmoment.common.testing.TestCase;

@AllArgsConstructor
public class GroovyParametersStrategy implements ParametersStrategy {
	@Setter private String path;

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
