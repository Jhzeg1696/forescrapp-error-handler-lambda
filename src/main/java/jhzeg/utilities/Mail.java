package jhzeg.utilities;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	private String host;
	private String to;
	private String from;
	private String user;
	private String password;
	
	public Mail(String host, String to, String from, String user, String password)
	{
		this.host = host;
		this.to = to;
		this.from = from;
		this.user = user;
		this.password = password;
	}
	
	public String send(String text)
	{
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
            
        });
        
        session.setDebug(true);
        
        try 
        {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Forescrapp error");

            // Now set the actual message
            message.setText(text);

            System.out.println("Sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }
        
        catch (MessagingException messagingException) 
        {
            messagingException.printStackTrace();
        }
        
        return "Error Message sended correctly";
	}
}
