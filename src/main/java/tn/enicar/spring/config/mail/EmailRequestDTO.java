package tn.enicar.spring.config.mail;

public class EmailRequestDTO {
	
	//initialisation with configuration
	private String from="kindergartenplatform@gmail.com";
    private String to;
    private String subject;
    private String name;
    
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
    
    
    

}
