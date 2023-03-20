package com.management.projects.util;

import org.junit.jupiter.api.Test;

import static com.management.projects.util.RegexValidator.isEmail;
import static org.junit.jupiter.api.Assertions.*;

class RegexValidatorIsEmailTest {

    @Test
    public void isEmail_true_when_has_only_letter_before_At_symbol(){
        String email = "user@mail.com";
        assertTrue(isEmail(email));
    }

    @Test
    public void isEmail_true_when_has_only_number_before_At_symbol(){
        String email = "23456@mail.com";
        assertTrue(isEmail(email));
    }

    @Test
    public void isEmail_true_when_has_number_and_letter_before_At_symbol(){
        String email = "0user123@mail.com";
        assertTrue(isEmail(email));
    }

    @Test
    public void isEmail_true_when_has_upper_and_camel_case_letter_before_At_symbol(){
        String email = "USER@mail.com";
        String email1 = "UserMail@mail.com";
        assertTrue(isEmail(email));
        assertTrue(isEmail(email1));
    }

    @Test
    public void isEmail_true_when_has_dots_before_At_symbol(){
        String email = "user.mail@mail.com";
        String email1 = "user.mail.mail@mail.com";
        String email2 = "user.mail.mail.@mail.com";
        assertTrue(isEmail(email));
        assertTrue(isEmail(email1));
        assertTrue(isEmail(email1));
    }

    @Test
    public void isEmail_true_when_has_bar_and_low_bar_before_At_symbol(){
        String email = "user-mail@mail.com";
        String email1 = "user_mail@mail.com";
        String email2 = "user_mail-mail_@mail.com";
        assertTrue(isEmail(email));
        assertTrue(isEmail(email1));
        assertTrue(isEmail(email2));
    }

    @Test
    public void isEmail_false_when_nothing_before_At_symbol(){
        String email = "@mail.com";
        assertFalse(isEmail(email));
    }

    @Test
    public void isEmail_false_when_have_parenthesis_before_At_symbol(){
        String email = "user()@mail.com";
        assertFalse(isEmail(email));
    }

    @Test
    public void isEmail_false_when_have_characters_before_At_symbol(){
        String email = "user@@mail.com";
        String email1 = "user/!#$%^&*+@mail.com";
        String email2 = "!#$%^&*+@mail.com";
        String email3 = "!#$%^&*+User@mail.com";
        assertFalse(isEmail(email));
        assertFalse(isEmail(email1));
        assertFalse(isEmail(email2));
        assertFalse(isEmail(email3));
    }

    @Test
    public void isEmail_false_when_after_At_symbol_ends_with_more_than_one_dot_bar_or_low_bar(){
        String email = "user@mail....com";
        String email1 = "user@mail..com";
        assertFalse(isEmail(email));
        assertFalse(isEmail(email1));
    }

    @Test
    public void isEmail_true_when_after_At_symbol_have_mail_with_multiple_extensions(){
        String email = "user@mail.example.com";
        String email1 = "user@mail.-example-.com";
        String email2 = "user@mail_.si-te.example.com";
        assertTrue(isEmail(email));
        assertTrue(isEmail(email1));
        assertTrue(isEmail(email2));
    }

    @Test
    public void isEmail_true_when_end_between_2_and_6_letters_or_numbers(){
        String email = "user@mail.example.CO";
        String email1 = "user@mail.com";
        String email2 = "user@mail.Com12";
        String email3 = "user@mail.123456";
        assertTrue(isEmail(email));
        assertTrue(isEmail(email1));
        assertTrue(isEmail(email2));
        assertTrue(isEmail(email3));
    }

    @Test
    public void isEmail_true_when_end_in_1_or_more_than_6_letters_or_numbers(){
        String email = "user@mail.example.C";
        String email1 = "user@mail.abc456G8";
        assertFalse(isEmail(email));
        assertFalse(isEmail(email1));
    }

}