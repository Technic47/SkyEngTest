package com.testcase.skyeng.models;

import static com.testcase.skyeng.models.additions.PackageType.MAIL;

public class TestCredentials {
    public static final Long TEST_ID = 666L;
    public static final String TEST_NAME = "testName";
    public static final String TEST_ADDRESS_LINE = "testAddress";
    public static final Long TEST_PASSPORT = 123456789L;
    public static final int TEST_INDEX = 987456;
    public static final String TEST_LINK = "testLink";
    public static final String TEST_VALUE = "testValue";
    public static final Long TEST_CREATOR = 3L;
    public static final Long TEST_MANUFACTURER_ID = 33L;
    public static final String TEST_MODEL = "testModel";
    public static final String TEST_PASS = "testPass";
    public static final Address TEST_ADDRESS = new Address(TEST_ID, TEST_NAME, TEST_NAME, TEST_ADDRESS_LINE, TEST_ADDRESS_LINE, TEST_INDEX);
    public static final Person TEST_PERSON = new Person(TEST_ID, TEST_PASSPORT, TEST_NAME, TEST_NAME, TEST_ADDRESS);
    public static final MailPackage TEST_MAILPACKAGE = new MailPackage(TEST_ID, MAIL, TEST_INDEX, TEST_ADDRESS, TEST_PERSON);
    public static final PostOffice TEST_POSTOFFICE = new PostOffice(TEST_ID, TEST_NAME, TEST_INDEX, TEST_ADDRESS);
}
