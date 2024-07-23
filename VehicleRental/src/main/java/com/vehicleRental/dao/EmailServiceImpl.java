
package com.vehicleRental.dao;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.vehicleRental.service.EmailService;


@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender mailSend;
	@Override
	public String sendSimpleMail(String to, String body, String sub) {
		// TODO Auto-generated method stub
		SimpleMailMessage msg= new SimpleMailMessage();
		msg.setFrom("grabyourrideg5@gmail.com");
		msg.setTo(to);
		msg.setText(body);
		msg.setSubject(sub);
		mailSend.send(msg);
		return "Mail send";
	}

	

}
