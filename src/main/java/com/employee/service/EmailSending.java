package com.employee.service;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailSending
{
	public static boolean sendEmail(String massage,String subject,String to) throws Exception
	{

		boolean flag=false;
		//from is constant here
		String from="nirajverma53942178@gmail.com";

		//set the host
		String host="smtp.gmail.com";

		//get the system propertise
		Properties properties = System.getProperties();
		System.out.println("PROPERTISE "+properties);

		//setting the imporatnt propertise
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		//1)---->get the session object
		Session session = Session.getInstance(properties, new Authenticator() 
		{

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("nirajverma53942178@gmail.com", "elbotqaldaferoup");
			}

		});

		session.setDebug(true);

		//2)------->compose the massage
		MimeMessage mimeMessage = new MimeMessage(session);

		//send email to---KON BHEJRAHA HAI
		mimeMessage.setFrom(from);

		//adding recipient to massage---> KISAKO BHEJNA HAI
		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		//adding subject to masssage--->SUBJECT KYA HAI
		mimeMessage.setSubject(subject);


		//send attachment to massage--->ATTACHMENT KYA HAI
		String path="C:\\Users\\bunty\\Desktop\\Image.png";//---> attachemnt file on dextop

		MimeMultipart mimeMultipart = new MimeMultipart();


		//this is for sending text
		MimeBodyPart textMime = new MimeBodyPart();
		textMime.setText(massage);

		//this is for sending attachment
		MimeBodyPart fileMime = new MimeBodyPart();
		java.io.File file = new java.io.File(path);
		fileMime.attachFile(file);


		//textMine and fileMine DONO KO 	MimeMultipart me dalna hai!!!
		mimeMultipart.addBodyPart(textMime);
		mimeMultipart.addBodyPart(fileMime);

		//mime massage -----> pass ------> mime multipart
		mimeMessage.setContent(mimeMultipart);

		//sending massage using method transport
		Transport.send(mimeMessage);
		System.out.println("Send Sucessfully");

		flag=true;

		return flag;
	}
}

