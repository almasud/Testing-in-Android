package com.example.testinginandroid;

import com.example.testinginandroid.ui.utils.EmailValidator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the EmailValidator using JUnit framework
 *
 * @author Abdullah Almasud
 */
public class EmailValidatorTest {

    @Test
    public void emailValidator_CorrectEmail_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.com"));
    }

    @Test
    public void emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.com.bd"));
    }

    @Test
    public void emailValidator_InvalidEmailNoLastPart_ReturnsFalse() {
        assertTrue(EmailValidator.isValidEmail("name@email"));
    }

    @Test
    public void emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertTrue(EmailValidator.isValidEmail("name@email..com"));
    }

    @Test
    public void emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertTrue(EmailValidator.isValidEmail("@email.com"));
    }

    @Test
    public void emailValidator_EmptyString_ReturnsFalse() {
        assertTrue(EmailValidator.isValidEmail(""));
    }

    @Test
    public void emailValidator_NullEmail_ReturnsFalse() {
        assertTrue(EmailValidator.isValidEmail(null));
    }
}