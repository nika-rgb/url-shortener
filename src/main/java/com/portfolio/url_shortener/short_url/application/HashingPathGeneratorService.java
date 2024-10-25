package com.portfolio.url_shortener.short_url.application;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class HashingPathGeneratorService implements ShortPathGeneratorService {
    private static final String HASHING_ALGORITHM = "MD5";

    @Override
    public String generateUniquePath(String originalUrl, int pathSize) {
        try {

            String randomSalt = generateRandomSalt();

            MessageDigest messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM);

            String valueToHash = originalUrl.concat(randomSalt);

            messageDigest.update(valueToHash.getBytes(StandardCharsets.UTF_8));

            byte[] hashResult = messageDigest.digest();

            return getPath(hashResult, pathSize);
        } catch (NoSuchAlgorithmException e) {
            throw new URLGenerationFailed("URL genration failed", e);
        }
    }

    private String getPath(byte[] hashResult, int pathSize) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte currentByte : hashResult) {
            stringBuilder.append(Integer.toHexString(currentByte & 0xff));

            if (stringBuilder.length() >= pathSize)
                return stringBuilder.substring(0, pathSize);

        }

        return stringBuilder.substring(0, pathSize);
    }

    // Generate random salt to ensure uniqueness
    private String generateRandomSalt() {
        SecureRandom random = new SecureRandom();
        byte[] randomByteArr = new byte[16];
        random.nextBytes(randomByteArr);
        return new String(randomByteArr, StandardCharsets.UTF_8);
    }
}
