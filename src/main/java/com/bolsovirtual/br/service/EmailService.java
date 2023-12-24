package com.bolsovirtual.br.service;

import com.bolsovirtual.br.domain.Usuario;
import com.bolsovirtual.br.request.UsuarioRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final String EMAIL_SITE = "bolsovirtual.com@gmail.com";
    public void sendEmail(Usuario entity) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
        mimeMessageHelper.setFrom(EMAIL_SITE);
        mimeMessageHelper.setTo(entity.getEmail());
        mimeMessageHelper.setSubject("**Código de verificação**");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm   dd/MM/yyyy");
        String formattedDateTime = entity.getDataCodigoVerificacao().format(formatter);
        mimeMessageHelper.setText("Olá "+entity.getNome()+" segue seu código de verificação : "+
                entity.getCodigoVerificacao()+". Data do código : "+formattedDateTime+", seu código expira em 15 minutos.");
        javaMailSender.send(message);
    }
}
