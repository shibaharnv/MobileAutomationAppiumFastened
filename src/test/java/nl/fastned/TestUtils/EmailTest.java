package nl.fastned.TestUtils;

import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailTest {

    /**
     * Sends an email with an HTML report attachment to the specified recipient.
     *
     * @param recipient the email address of the recipient
     * @throws IOException if an I/O error occurs during email configuration
     */

    public static void emailTrigger(String recipient) throws IOException {

        // Create object of Property file
        Properties props = new Properties();

        // this will set host of server- you can change based on your requirement
        props.put("mail.smtp.host", "smtp.gmail.com");

        // set the port of socket factory
        props.put("mail.smtp.socketFactory.port", "465");

        // set socket factory
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

        // set the authentication to true
        props.put("mail.smtp.auth", "true");

        // set the port of SMTP server
        props.put("mail.smtp.port", "465");

        // This will handle the complete authentication
        Session session = Session.getDefaultInstance(props,

                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication("shibaharautomation@gmail.com", "dayawqeeisdvwkmv");

                    }

                });

        try {

            // Create object of MimeMessage class
            Message message = new MimeMessage(session);
            // Set the from address
            message.setFrom(new InternetAddress("shibaharautomation@gmail.com"));

            // Set the recipient address
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recipient));

            // Add the subject link
            message.setSubject("Fast ned Mobile Automation Report");

            //messageBodyPart1
            BodyPart messageBodyPart1 = new MimeBodyPart();


            String fileName= System.getProperty("user.dir")+"/reports/index.html";
            String fileFirst="file://";
           String fileNameFull=fileFirst +fileName;

            String messagtosend = "Hi,"
                    + "<br><br><b>Please refer the attached HTML report for automation execution results. </b>"
                    + "<br><br>Refer the link below to access the report:<br>"
                    + "<a href=\"" + fileNameFull + "\">" + fileNameFull + "</a>"
                    + "<br><br>Thanks and Regards,"
                    + "<br>Shibahar Nagarajan";


            messageBodyPart1.setContent(messagtosend, "text/html; charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
            message.setContent(multipart);

            // finally send the email
            Transport.send(message);


            System.out.println("=====Email Sent With Execution Status Report=====");
        } catch (MessagingException e) {

            throw new RuntimeException(e);

        }
    }
}
