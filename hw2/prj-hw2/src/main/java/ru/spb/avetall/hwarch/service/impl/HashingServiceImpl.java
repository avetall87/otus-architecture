package ru.spb.avetall.hwarch.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.spb.avetall.hwarch.service.HashingService;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Slf4j
@Service
public class HashingServiceImpl implements HashingService {

    private final String SALT;

    public HashingServiceImpl(@Value("${application.password.salt}") String SALT) {
        if (isBlank(SALT)) {
            this.SALT = "someSecretKey";
        } else {
            this.SALT = SALT;
        }
    }

    @Override
    @SneakyThrows({NoSuchAlgorithmException.class, InvalidKeySpecException.class})
    public byte[] generateHash(String password) {
        int KEY_LENGTH = 1024;
        int ITERATION_COUNT = 65536;


        KeySpec spec = new PBEKeySpec(password.toCharArray(), SALT.getBytes(), ITERATION_COUNT, KEY_LENGTH);
        String ALGORITHM_INSTANCE_NAME = "PBKDF2WithHmacSHA1";
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM_INSTANCE_NAME);
        
        return factory.generateSecret(spec).getEncoded();
    }

}
