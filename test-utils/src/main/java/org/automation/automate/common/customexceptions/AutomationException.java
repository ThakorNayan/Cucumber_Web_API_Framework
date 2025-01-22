package org.automation.automate.common.customexceptions;

public class AutomationException extends RuntimeException {
	public AutomationException() {
		super();
	}

	public AutomationException(String message) {
		super(message);
	}

	public AutomationException(String message, Exception e) {
		super(message, e);
	}
}
