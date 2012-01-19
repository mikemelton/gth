package org.helicalmoment.common.test.junit.runners;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Setter;

import org.helicalmoment.common.test.TestCase;

@AllArgsConstructor
public class GroovyParametersStrategy implements ParametersStrategy {
	@Setter private String path;

	@SuppressWarnings("unchecked")
	public List<? extends TestCase> getTestCases() throws IOException {
		GroovyShell gs = new GroovyShell(new Binding());
		// TODO: Handle file location better
		Reader r = new InputStreamReader(getClass().getClassLoader().getResource(path).openStream());
		Object contents = gs.evaluate(r);
		if (!(contents instanceof List<?>))
			throw new IllegalStateException(String.format("Expecting List<TestCase>, got %s", contents.getClass()));
		
		// TODO: Type-check each element?
		
		return (List<TestCase>) contents;
	}
}
