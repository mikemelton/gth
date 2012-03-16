package org.helicalmoment.common.testing.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.helicalmoment.common.testing.TestParameters;
import org.helicalmoment.common.testing.TestStep;

@Getter @RequiredArgsConstructor @EqualsAndHashCode
public abstract class AbstractTestStep<T extends TestParameters> implements TestStep<T> {
	@NonNull private final String name;
	@NonNull private final String description; 
}
