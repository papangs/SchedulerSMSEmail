package com;

import org.junit.Test;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

class SpringBootMybatisApplicationTests {

	// Find your Account SID and Auth Token at twilio.com/console
	// and set the environment variables. See http://twil.io/secure
	public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
	public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

	@Test
	void contextLoads() {
	}

//    @Test
//    public void testEncryptionKey() {
//        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
//        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
//        config.setPassword("javatechie"); // encryptor's private key
//        config.setAlgorithm("PBEWithMD5AndDES");
//        config.setKeyObtentionIterations("1000");
//        config.setPoolSize("1");
//        config.setProviderName("SunJCE");
//        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
//        config.setStringOutputType("base64");
//        encryptor.setConfig(config);
//
//        String plaintext = "";
//        System.out.println("Encrypted key : " + encryptor.encrypt(plaintext));
//    }

	@Test
	public void tes() {
		// Install the Java helper library from twilio.com/docs/java/install

		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message.creator(
				new com.twilio.type.PhoneNumber("+62832095434"),
				new com.twilio.type.PhoneNumber("+62832095434"), 
				"Where's Wallace?").create();

		System.out.println(message.getSid());
	}
}
