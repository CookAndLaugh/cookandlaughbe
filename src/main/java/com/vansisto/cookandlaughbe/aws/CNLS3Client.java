package com.vansisto.cookandlaughbe.aws;

import com.vansisto.cookandlaughbe.exception.S3StoringException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class CNLS3Client {
    private final S3Client s3Client;

    public void storeFile(MultipartFile file, String bucketName, String key) {
        log.info("Storing file {} to bucket {} with key {}", file, bucketName, key);

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
        } catch (S3Exception e) {
            throw new S3StoringException(file.getName(), bucketName, e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Unable to get bytes from file: " + e); //TODO: Handle this exception properly
        }
    }
}
