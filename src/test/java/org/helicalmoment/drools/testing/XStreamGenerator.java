package org.helicalmoment.drools.testing;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import org.helicalmoment.common.testing.TestParameters;
import org.helicalmoment.common.testing.TestStep;
import org.helicalmoment.common.testing.impl.DefaultTestCase;

import com.thoughtworks.xstream.XStream;

@Slf4j
public class XStreamGenerator {
	static int x = 0;
	
	static class TestTestParameters implements TestParameters {
		public int getId() {
			return x;
		}
	}
	
	static class TestTestCase extends DefaultTestCase<TestTestParameters> {
		@Getter @Setter private int id;
		
		public TestTestCase(String name, String description, boolean ignored) {
			super(name, description, ignored);
			this.id = x++;
		}

		@Override
		public TestTestParameters getTestParameters() {
			return new TestTestParameters();
		}
	}
	
	@AllArgsConstructor @ToString @Getter
	static class TestTestStep implements TestStep<TestTestParameters> {
		private final String name;
		private final String description;
		public void execute(TestTestParameters parms) {
			log.info("Test step executing, id={}", parms.getId());
		}
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		TestTestCase t1 = new TestTestCase("Test Scenario 1", "This is a test which does some stuff and some other stuff.", false);
		t1.addStep(new TestTestStep("name1", "desc1"));
		TestTestCase t2 = new TestTestCase("Test Scenario 2", "This is another test.", false);
		List<TestTestCase> list = new ArrayList<TestTestCase>();
		list.add(t1);
		list.add(t2);
		
		XStream x = new XStream();
		OutputStream out = new FileOutputStream("c:\\tmp\\XStreamDataSource.xml");
		x.toXML(list, out);
		
		System.out.println("Done");
	}
}
