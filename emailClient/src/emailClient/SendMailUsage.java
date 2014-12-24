package emailClient;

import java.util.*;
import java.io.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.apache.commons.exec.ExecuteException;
 
public class SendMailUsage {
 
	public static Properties props = new Properties();

    
    public static void main(String[] args) throws ExecuteException, IOException, InterruptedException {
 
    	props.put("mail.smtp.from","<>"); // From Mail ID
    	props.put("mail.smtp.to","<>"); // To Mail ID
    	props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        Boolean isConnected = Boolean.FALSE;
        // Get a session
        Session session = Session.getInstance(props);
 
        try {
            // Get a Transport object to send e-mail
            Transport bus = session.getTransport("smtp");
 
            // Connect only once here
            // Transport.send() disconnects after each send
            // Usually, no username and password is required for SMTP
            //bus.connect();
            do {
            	try {
            		bus.connect((String)props.get("mail.smtp.host"),(String)props.get("mail.smtp.from"), "<>"); //passwoed
            		isConnected= Boolean.TRUE;
            	} catch (Exception e) {
            		isConnected= Boolean.FALSE;
            		System.out.println("\nConnected Status :"+isConnected +" Sleaping For 60 sec");
            		Thread.sleep(60000);
            	}   
            }while (!isConnected);

            // Instantiate a message
            Message msg = new MimeMessage(session);
 
            // Set message attributes
            msg.setFrom(new InternetAddress((String)props.get("mail.smtp.from")));
            InternetAddress[] address = {new InternetAddress((String)props.get("mail.smtp.to"))};
            msg.setRecipients(Message.RecipientType.TO, address);
            // Parse a comma-separated list of email addresses. Be strict.
            msg.setRecipients(Message.RecipientType.CC,
                                InternetAddress.parse((String)props.get("mail.smtp.to"), true));
            // Parse comma/space-separated list. Cut some slack.
            msg.setRecipients(Message.RecipientType.BCC,
                                InternetAddress.parse((String)props.get("mail.smtp.to"), false));
 
            
            msg.setSubject("E-Mail:"+System.getProperty("os.name")+"-"+System.getProperty("os.version")+"-"+System.getProperty("user.name") +"-"+ IfSh.getDIP());
            msg.setSentDate(new Date());
 
            // Set message content and send
          /*  setTextContent(msg);
            msg.saveChanges();
            bus.sendMessage(msg, address);*/
 
           /* setMultipartContent(msg);
            msg.saveChanges();
            bus.sendMessage(msg, address);*/
 
          /*  setFileAsAttachment(msg, "C:/WINDOWS/CLOUD.GIF");
            msg.saveChanges();
            bus.sendMessage(msg, address);*/
            setHTMLContent(msg);
            msg.saveChanges();
            bus.sendMessage(msg, address);
 
            bus.close();
 
        }
        catch (MessagingException mex) {
            // Prints all nested (chained) exceptions as well
            mex.printStackTrace();
            // How to access nested exceptions
            while (mex.getNextException() != null) {
                // Get next exception in chain
                Exception ex = mex.getNextException();
                ex.printStackTrace();
                if (!(ex instanceof MessagingException)) break;
                else mex = (MessagingException)ex;
            }
        }
    }
 
    // A simple, single-part text/plain e-mail.
    public static void setTextContent(Message msg) throws MessagingException {
            // Set message content
            String mytxt = "This is a test of sending a " +
                            "plain text e-mail through Java.\n" +
                            "Here is line 2.";
            msg.setText(mytxt);
 
            // Alternate form
            msg.setContent(mytxt, "text/plain");
 
    }
 
    // A simple multipart/mixed e-mail. Both body parts are text/plain.
    public static void setMultipartContent(Message msg) throws MessagingException {
        // Create and fill first part
        MimeBodyPart p1 = new MimeBodyPart();
        p1.setText("This is part one of a test multipart e-mail.");
 
        // Create and fill second part
        MimeBodyPart p2 = new MimeBodyPart();
        // Here is how to set a charset on textual content
        p2.setText("This is the second part", "us-ascii");
 
        // Create the Multipart.  Add BodyParts to it.
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(p1);
        mp.addBodyPart(p2);
 
        // Set Multipart as the message's content
        msg.setContent(mp);
    }
 
    // Set a file as an attachment.  Uses JAF FileDataSource.
    public static void setFileAsAttachment(Message msg, String filename)
             throws MessagingException {
 
        // Create and fill first part
        MimeBodyPart p1 = new MimeBodyPart();
        p1.setText("This is part one of a test multipart e-mail." +
                    "The second part is file as an attachment");
 
        // Create second part
        MimeBodyPart p2 = new MimeBodyPart();
 
        // Put a file in the second part
        FileDataSource fds = new FileDataSource(filename);
        p2.setDataHandler(new DataHandler(fds));
        p2.setFileName(fds.getName());
 
        // Create the Multipart.  Add BodyParts to it.
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(p1);
        mp.addBodyPart(p2);
 
        // Set Multipart as the message's content
        msg.setContent(mp);
    }
 
    // Set a single part html content.
    // Sending data of any type is similar.
    public static void setHTMLContent(Message msg) throws MessagingException {
 
        String html = "<html><head><title>" +
                        msg.getSubject() +
                        "</title></head><body><h1>" +
                        msg.getSubject() +
                        "</h1><p>This is a test of sending an HTML e-mail" +
                        " through Java.</body></html>";
 
        // HTMLDataSource is a static nested class
        msg.setDataHandler(new DataHandler(new HTMLDataSource(html)));
    }
 
    /*
     * Static nested class to act as a JAF datasource to send HTML e-mail content
     */
    static class HTMLDataSource implements DataSource {
        private String html;
 
        public HTMLDataSource(String htmlString) {
            html = htmlString;
        }
 
        // Return html string in an InputStream.
        // A new stream must be returned each time.
        public InputStream getInputStream() throws IOException {
            if (html == null) throw new IOException("Null HTML");
            return new ByteArrayInputStream(html.getBytes());
        }
 
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("This DataHandler cannot write HTML");
        }
 
        public String getContentType() {
            return "text/html";
        }
 
        public String getName() {
            return "JAF text/html dataSource to send e-mail only";
        }
    }
 
} //End of class