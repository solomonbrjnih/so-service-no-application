package foo.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.awspring.cloud.s3.S3Template;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class DemoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoService.class);

    @Autowired
    private S3Template s3Template;

    public BucketInfo bucketInfo(String name) {
        boolean exists;
        try {
            exists = s3Template.bucketExists(name);
        } catch (S3Exception e) {
            if (e.statusCode() == 403) {
                LOGGER.info("Bucket={} may exist but is forbidden", name);
                exists = false;
            } else {
                throw e;
            }
        }
        return new BucketInfo(name, exists);
    }
}
