package tn.enicar.spring.service.implementation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import freemarker.template.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import tn.enicar.spring.config.mail.EmailRequestDTO;
import tn.enicar.spring.services.interfaces.IMailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javax.mail.MessagingException;

@Service
public class MailServiceImpl implements IMailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private Configuration configuration;

	@Override
	public void sendSimpleMail(String destination, String subject, String text) {

		// Create a Simple MailMessage.
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(destination);
		message.setSubject(subject);
		message.setText(text);

		// Send Message!
		this.emailSender.send(message);

	}

	@Override
	public String sendMailWithFreeMarker(EmailRequestDTO request, Map<String, String> model, String t) {

		
		
		String response;
		MimeMessage message = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			Template template = configuration.getTemplate(t);
			
					
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

			helper.setTo(request.getTo());
			helper.setFrom(request.getFrom());
			helper.setSubject(request.getSubject());
			helper.setText(html, true);
			

			emailSender.send(message);
			response = "Email has been sent to :" + request.getTo();
		} catch (MessagingException | IOException | TemplateException e) {
			response = "Email send failure to :" + request.getTo();
		}
		return response;
	}

}
