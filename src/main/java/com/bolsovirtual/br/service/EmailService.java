package com.bolsovirtual.br.service;

import com.bolsovirtual.br.request.UsuarioRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final String EMAIL_SITE = "sabor.em.acao@gmail.com";
    public void sendEmail(UsuarioRequest request) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
        mimeMessageHelper.setFrom(EMAIL_SITE);
        mimeMessageHelper.setTo(request.getEmail());
        mimeMessageHelper.setSubject("**Código de verificação**");
        mimeMessageHelper.setText("Olá "+request.getNome()+" segue seu código de verificação : "+request.getCodigoVerificacao());
        javaMailSender.send(message);
    }
}
