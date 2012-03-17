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

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Setter;

import org.helicalmoment.common.testing.TestCase;

import com.thoughtworks.xstream.XStream;

/**
 * A {@link ParametersStrategy} implementation which reads an XML file to load
 * test cases in XStream format. As with all {@code ParametersStrategy}
 * implementations, the expected output is {@code List<TestCase>}.
 */
@AllArgsConstructor
public class XStreamParametersStrategy implements ParametersStrategy {
	@Setter
	private String path;

	/**
	 * Gets the test cases from the XStream file defined by {@code path}.
	 * 
	 * @throws IOException
	 *             if there is an error accessing or reading the file
	 * @throws IllegalStateException
	 *             if the XStream data does not represent in a {@code List}
	 */
	@SuppressWarnings("unchecked")
	public List<? extends TestCase<?>> getTestCases() throws MalformedURLException {
		XStream stream = new XStream();
		stream.setMode(XStream.ID_REFERENCES);
		// TODO: Handle file location better
		Object contents = stream.fromXML(getClass().getClassLoader().getResource(path));
		if (!(contents instanceof List<?>))
			throw new IllegalStateException(String.format("Expecting List<TestCase>, got %s", contents.getClass()));

		// TODO: Type-check each element?

		return (List<TestCase<?>>) contents;
	}
}