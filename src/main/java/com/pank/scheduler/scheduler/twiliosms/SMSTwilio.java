package com.pank.scheduler.scheduler.twiliosms;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SMSTwilio {

	public static final String ACCOUNT_SID = "AC1eff6d3633fad6a26477f1da516ce918";
    public static final String AUTH_TOKEN = "3f77d92b23395f3a026826c0f483bf35";

    private static String param = "Della";
    
    public void twilio() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+6285741217228"),
                new com.twilio.type.PhoneNumber("+14405776422"),
                "Hai, Kamu Abi ya dari PT Fortuna Mediatama aq "+param).create();

        System.out.println(message.getSid());
    }
}