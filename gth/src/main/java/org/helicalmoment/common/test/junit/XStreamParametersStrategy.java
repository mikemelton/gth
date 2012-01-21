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

package org.helicalmoment.common.test.junit;

import java.net.MalformedURLException;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Setter;

import org.helicalmoment.common.test.TestCase;

import com.thoughtworks.xstream.XStream;

@AllArgsConstructor
public class XStreamParametersStrategy implements ParametersStrategy {
	private @Setter String path;

	@SuppressWarnings("unchecked")
	public List<? extends TestCase> getTestCases() throws MalformedURLException {
		XStream stream = new XStream();
		stream.setMode(XStream.ID_REFERENCES);
		// TODO: Handle file location better
		Object contents = stream.fromXML(getClass().getClassLoader().getResource(path));
		if (!(contents instanceof List<?>))
			throw new IllegalStateException(String.format("Expecting List<TestCase>, got %s", contents.getClass()));
		
		// TODO: Type-check each element?
		
		return (List<TestCase>) contents;
	}
}