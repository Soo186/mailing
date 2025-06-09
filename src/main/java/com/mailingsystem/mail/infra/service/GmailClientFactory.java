package com.mailingsystem.mail.infra.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

@Component
public class GmailClientFactory {

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public GmailClient create(String credentialJsonPath) {
        try {
            InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(credentialJsonPath);
            if (resourceStream == null) {
                throw new FileNotFoundException("리소스 파일을 찾을 수 없습니다: " + credentialJsonPath);
            }
            InputStreamReader reader = new InputStreamReader(resourceStream);

            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, reader);

            // 토큰 저장 경로 명시
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JSON_FACTORY,
                    clientSecrets,
                    List.of("https://www.googleapis.com/auth/gmail.send")
            )
                    .setDataStoreFactory(new FileDataStoreFactory(new File("src/main/resources/tokens")))
                    .setAccessType("offline")
                    .build();

            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
            Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

            Gmail gmail = new Gmail.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JSON_FACTORY,
                    credential
            ).setApplicationName("mailing-system").build();

            return new GmailClientWrapper(new GmailClientImpl(gmail), credentialJsonPath);

        } catch (Exception e) {
            throw new RuntimeException("Gmail API 인증 실패: " + credentialJsonPath, e);
        }
    }
}
