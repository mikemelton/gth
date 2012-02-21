package org.helicalmoment.common.testing.impl;

import lombok.Getter;

import org.helicalmoment.common.testing.TestParameters;
import org.helicalmoment.common.testing.TestStep;

@Getter
public abstract class AbstractTestStep<T extends TestParameters> implements TestStep<T> {
	private String name;
	private String description;
}
