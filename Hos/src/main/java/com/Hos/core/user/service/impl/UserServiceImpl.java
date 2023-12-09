package com.Hos.core.user.service.impl;

import com.Hos.core.common.model.User;
import com.Hos.core.user.repository.UserRepository;
import com.Hos.core.user.service.UserService;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private Random random = new Random();

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<User> getAllUsers() {
        return userRepository.findByIsDeletedFalse();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsernameAndIsDeletedFalse(username);
    }

    private String generateUserOtp(String oldOtp) {
        String otp = null;
        boolean isCodeGenerated = Boolean.FALSE;
        while (!isCodeGenerated) {
            otp = String.valueOf(random.nextInt(900000) + 100000);
            if (!otp.equals(oldOtp)) isCodeGenerated = Boolean.TRUE;
        }
        return otp;
    }


    public String sendOtp(String username) {
        User user = getUserByUsername(username);
        String response = null;
        String otp = generateUserOtp(user.getOtp());
        if (Objects.nonNull(user)) {
            String body = "HOS Security Code:\n" + otp +
                    " is the code to complete your login process.\n" +
                    "Do not share this code with anyone for security reasons.";
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "email-smtp.ap-south-1.amazonaws.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("AKIA42P3OJNQHY734BXC", "BDXHNTytW4NDwLBVHJWtr2S8PPIlnshJmaoWhViotv5w");
                }
            };
            try {
                Session session = Session.getInstance(properties, auth);
                MimeMessage mimeMessage = new MimeMessage(session);
                mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
                mimeMessage.addHeader("format", "flowed");
                mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");
                mimeMessage.setFrom("MDTLabs <no-reply@medtroniclabs.org>");
                mimeMessage.setReplyTo(InternetAddress.parse("MDTLabs <no-reply@medtroniclabs.org>", false));
                mimeMessage.setSubject("OTP Notification", "UTF-8");
                mimeMessage.setContent(body, "text/html; charset=utf-8");
                mimeMessage.setSentDate(new Date());
                mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
                        StringUtils.isNotBlank("tamilarasi.shanmugasundaram@ideas2it.com") ? "tamilarasi.shanmugasundaram@ideas2it.com" : "", false));
//                mimeMessage.setRecipients(Message.RecipientType.CC, InternetAddress
//                        .parse(StringUtils.isNotBlank(emailDto.getCc()) ? emailDto.getCc() : Constants.EMPTY, false));
//                mimeMessage.setRecipients(Message.RecipientType.BCC, InternetAddress
//                        .parse(StringUtils.isNotBlank(emailDto.getBcc()) ? emailDto.getBcc() : Constants.EMPTY, false));
                Transport.send(mimeMessage);
                response = "Otp send Successfully";
            } catch (Exception e) {
                response = "excep";
            }
        }
        return response;
    }
}
