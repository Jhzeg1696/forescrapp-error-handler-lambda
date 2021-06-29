package jhzeg.handler;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jhzeg.pojos.ErrorMessage;
import jhzeg.utilities.Mail;

public class SendMail implements RequestHandler<Object, Object>{
	
	public Object handleRequest(Object input, Context context)
	{
		String host = "mail.emdpublicidad.com";
		String to = "jhzeg96@gmail.com";
		String from = "jahaziel@emdpublicidad.com";
		String user = "jahaziel@emdpublicidad.com";
		String password = "jahazielemd@01";
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		
		ErrorMessage errorMessage = gson.fromJson(gson.toJson(input), ErrorMessage.class);
		
		Mail mail = new Mail(host, to, from, user, password);
		
		String mailStatus = mail.send(errorMessage.getText());
		
		
        return mailStatus;
	}
}
