import groovy.transform.Canonical
import groovy.transform.ToString
import groovy.util.logging.Slf4j

import org.helicalmoment.common.testing.TestParameters
import org.helicalmoment.common.testing.TestStep
import org.helicalmoment.common.testing.impl.DefaultTestCase

class EmptyTestParameters implements TestParameters {}

@Slf4j
@Canonical
@ToString(includeNames=true)
class PrintTestStep implements TestStep<EmptyTestParameters> {
	String name
	String description
	
	public void execute(EmptyTestParameters parms) {
		log.info("Hello world! My name is [$name].")
	}	
}

@Slf4j
@Canonical
@ToString(includeNames=true)
class SquareTestStep implements TestStep<EmptyTestParameters> {
	String name
	String description
	int operand
	int expected
	
	public void execute(EmptyTestParameters parms) {
		assert operand * operand == expected : "${operand} squared is not equal to ${expected}"
	}	
}

@Canonical
class TestTestCase extends DefaultTestCase<EmptyTestParameters> {
	public TestTestCase(String name, String description, boolean ignored, List<TestStep<EmptyTestParameters>> steps) {
		super(name, description, ignored, steps);
	}
	
	public EmptyTestParameters getTestParameters() {
		return new EmptyTestParameters();
	}
}

def dc = new TestTestCase(
	"Groovy", 
	"Groovy test case", 
	false,
	[new PrintTestStep("name1", "desc1"), new PrintTestStep("name2", "desc2")])

def dc2 = new TestTestCase(
	"Groovy2", 
	"Groovy test case2", 
	false,
	[new PrintTestStep("groovy1", "descriptive description"), new SquareTestStep("foo", "bar", 4, 16)])

[dc, dc2]