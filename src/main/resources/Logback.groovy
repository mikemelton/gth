import static ch.qos.logback.classic.Level.DEBUG
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

appender("CONSOLE", ConsoleAppender) { 
	encoder(PatternLayoutEncoder) {
		pattern = "%d %-5p [%c{0}] %m%n"
	}
}
root(DEBUG, ["CONSOLE"])