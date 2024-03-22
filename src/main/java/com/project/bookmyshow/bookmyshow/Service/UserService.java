package com.project.bookmyshow.bookmyshow.Service;

import com.project.bookmyshow.bookmyshow.Entities.User;
import com.project.bookmyshow.bookmyshow.Repository.UserRepository;
//import com.twilio.http.TwilioRestClient;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.rest.api.v2010.account.MessageCreator;
//import com.twilio.type.PhoneNumber;
//import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    JavaMailSender javaMailSender;


    final static String ACCOUNT_SID = "AC9e067598cbd5ba28c088407a245a42dc";
    final static String  AUTH_TOKEN = "2f40d6a9ecd4bd54cfe96040dbc883f6";



    public String addUser(User user)
    {
        user=userRepository.save(user);


        //sending notification mail



        SimpleMailMessage message=new SimpleMailMessage();

        message.setTo(user.getEmail());
        message.setFrom("testmailforrohan@gmail.com");
        message.setSubject("Welcome to Book My Show");
        String body = "hi "+user.getName()+"! " + "You have successfully registered in bookMyShow application";

        message.setText(body);

        javaMailSender.send(message);


        //sending notification sms



//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message1 = Message.creator(
//                        new com.twilio.type.PhoneNumber("+91"+user.getMobileNo()),
//                        new com.twilio.type.PhoneNumber("+14434007303"),
//                        body)
//                .create();

        return "successfully added the user with id "+user.getUserId();
    }
}
