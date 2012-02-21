package org.helicalmoment.common.testing;

import org.helicalmoment.common.testing.TestCase;
import org.helicalmoment.common.testing.impl.AbstractTestHarness;
import org.helicalmoment.common.testing.junit.NamedParameterized;
import org.helicalmoment.common.testing.junit.UseParametersStrategy;
import org.helicalmoment.common.testing.junit.XStreamParametersStrategy;
import org.junit.runner.RunWith;

@UseParametersStrategy(clazz=XStreamParametersStrategy.class, path="org/helicalmoment/common/testing/XStreamDataSource.xml")
@RunWith(NamedParameterized.class)
public class XStreamTestHarness extends AbstractTestHarness {
	public XStreamTestHarness(TestCase<?> testCase) {
		super(testCase);
	}
}
