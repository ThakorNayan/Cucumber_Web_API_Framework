package org.automation.automate.common.utils;

import net.datafaker.Faker;

public class FakerUtil {
    private static final ThreadLocal<Faker> FAKER_THREAD_LOCAL = new ThreadLocal<Faker>() {
        @Override
        protected Faker initialValue() {
            return new Faker();
        }
    };

    private static Faker getFaker() {
        return FAKER_THREAD_LOCAL.get();
    }

    public static String firstName() {
        return getFaker().name().firstName();
    }

    public static String lastName() {
        return getFaker().name().lastName();
    }

    public static String fullName() {
        return getFaker().name().fullName();
    }

    public static String streetAddress() {
        return getFaker().address().streetAddress();
    }

    public static String city() {
        return getFaker().address().city();
    }

    public static String phoneNumber() {
        return getFaker().phoneNumber().phoneNumber();
    }

    public static String email() {
        return getFaker().internet().emailAddress();
    }

    public static String jobTitle() {
        return getFaker().job().title();
    }

}
