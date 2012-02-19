package org.helicalmoment.common.testing;

public interface TestStep<T extends TestParameters> {
	String getName();
	String getDescription();
	void execute(T parms);
}
