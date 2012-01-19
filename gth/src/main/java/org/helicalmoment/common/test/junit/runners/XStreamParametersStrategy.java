package org.helicalmoment.common.test.junit.runners;

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