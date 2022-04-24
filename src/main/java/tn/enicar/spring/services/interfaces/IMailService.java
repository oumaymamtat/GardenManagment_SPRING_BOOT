package tn.enicar.spring.services.interfaces;

import java.util.Map;

import tn.enicar.spring.config.mail.EmailRequestDTO;

public interface IMailService {

	public void sendSimpleMail(String destination, String subject, String text);

	public String sendMailWithFreeMarker(EmailRequestDTO request, Map<String, String> model,String template);

}
