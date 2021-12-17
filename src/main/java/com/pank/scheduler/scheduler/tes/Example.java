package com.pank.scheduler.scheduler.tes;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class Example {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "AC1eff6d3633fad6a26477f1da516ce918";
    public static final String AUTH_TOKEN = "3c64c7a66c1980ca60130e06a99f64f8";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+6285741217228"),
                new com.twilio.type.PhoneNumber("+14405776422"),
                "Coba TES").create();

        System.out.println(message.getSid());
    }
}