package org.automation.automate.ui.testdata;

import org.automation.automate.common.constants.Directory;
import org.automation.automate.common.utils.CommonUtil;
import org.automation.automate.common.utils.LoggerUtil;
import org.automation.automate.common.utils.PropertyUtil;

import static org.automation.automate.common.constants.Application.PASSWORD;
import static org.automation.automate.common.constants.Application.USERNAME;

public class Credentials {
	private static String ENV;
	private static final String _USERS = "_users";

	public Credentials() {
		ENV = CommonUtil.getEnvironment();
	}

	public String getUsername(String userRole) {
		LoggerUtil.logInfo("getUsername -> user role: " + userRole + " Environment: " + ENV);
		String username = CommonUtil.getValueForSystemVariable(userRole.toLowerCase() + "." + USERNAME);
		if (username == null || username.isEmpty()) {
			username = PropertyUtil.getProperty(Directory.UI_TESTS_CONFIG, ENV + _USERS, userRole + ".username");
		}
		return username;
	}

	public String getPassword(String userRole) {
		LoggerUtil.logInfo("getPassword -> user role: " + userRole + " Environment: " + ENV);
		String password = CommonUtil.getValueForSystemVariable(userRole.toLowerCase() + "." + PASSWORD);
		if (password == null || password.isEmpty()) {
			password = PropertyUtil.getProperty(Directory.UI_TESTS_CONFIG, ENV + _USERS, userRole + ".password");
		}
		return password;
	}
}
