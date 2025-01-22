package org.automation.automate.ui.testdata;

public enum UserRoles {
    TEST_USER("test-user"), SUPERVISOR("supervisor");
    public final String value;

    private UserRoles(String value) {
        this.value = value;
    }
}
