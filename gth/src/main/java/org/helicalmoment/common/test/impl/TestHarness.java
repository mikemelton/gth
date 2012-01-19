package org.helicalmoment.common.test.impl;

import lombok.AllArgsConstructor;

import org.helicalmoment.common.test.TestCase;
import org.junit.Test;

@AllArgsConstructor
public abstract class TestHarness {
	final private TestCase testCase;
	
	@Test
	public void runTestCase() {		
		testCase.executeTest();
	}
}
