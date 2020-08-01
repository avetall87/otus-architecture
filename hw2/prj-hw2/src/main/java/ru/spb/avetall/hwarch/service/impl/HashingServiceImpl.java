package ru.spb.avetall.hwarch.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.spb.avetall.hwarch.service.HashingService;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Slf4j
@Service
public class HashingServiceImpl implements HashingService {

    private final int SALT_LENGTH = 1024;
    private final int KEY_LENGTH = 1024;
    private final int ITERATION_COUNT = 65536;
    private final String ALGORITHM_INSTANCE_NAME = "PBKDF2WithHmacSHA1";
    private final String SALT = "someSecreteKey";
    
    @Override
    @SneakyThrows({NoSuchAlgorithmException.class, InvalidKeySpecException.class})
    public byte[] generateHash(String password) {
//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[SALT_LENGTH];
//        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(password.toCharArray(), SALT.getBytes(), ITERATION_COUNT, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM_INSTANCE_NAME);
        
        return factory.generateSecret(spec).getEncoded();
    }

}
