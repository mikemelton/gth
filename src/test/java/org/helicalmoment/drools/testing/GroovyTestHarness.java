package org.helicalmoment.drools.testing;

import org.helicalmoment.common.testing.TestCase;
import org.helicalmoment.common.testing.impl.AbstractTestHarness;
import org.helicalmoment.common.testing.junit.GroovyParametersStrategy;
import org.helicalmoment.common.testing.junit.NamedParameterized;
import org.helicalmoment.common.testing.junit.UseParametersStrategy;
import org.junit.runner.RunWith;

@UseParametersStrategy(clazz=GroovyParametersStrategy.class, path="org/helicalmoment/drools/test/GroovyDataSource.groovy")
@RunWith(NamedParameterized.class)
public class GroovyTestHarness extends AbstractTestHarness {
	public GroovyTestHarness(TestCase<?> testCase) {
		super(testCase);
	}
}
