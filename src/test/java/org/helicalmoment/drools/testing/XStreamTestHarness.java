package org.helicalmoment.drools.testing;

import org.helicalmoment.common.testing.TestCase;
import org.helicalmoment.common.testing.impl.AbstractTestHarness;
import org.helicalmoment.common.testing.junit.NamedParameterized;
import org.helicalmoment.common.testing.junit.UseParametersStrategy;
import org.helicalmoment.common.testing.junit.XStreamParametersStrategy;
import org.junit.runner.RunWith;

@UseParametersStrategy(clazz=XStreamParametersStrategy.class, path="org/helicalmoment/drools/test/XStreamDataSource.xml")
@RunWith(NamedParameterized.class)
public class XStreamTestHarness extends AbstractTestHarness {
	public XStreamTestHarness(TestCase<?> testCase) {
		super(testCase);
	}
}
