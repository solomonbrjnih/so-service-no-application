package foo.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private DemoService service;

	@Test
	void contextLoads() throws Exception {
		assertThat(service).isNotNull();
	}

	@Test
	void testBucketInfoFoo() throws Exception {
		BucketInfo info = service.bucketInfo("foo");
		assertThat(info.name()).isEqualTo("foo");
		assertThat(info.existsAndIsReadable()).isEqualTo(false);
	}

	@Test
	void testBucketInfoDNE() throws Exception {
		BucketInfo info = service.bucketInfo("foo-90823407813713045345");
		assertThat(info.existsAndIsReadable()).isEqualTo(false);
	}

	@Test
	void testBucketInfoShouldExist() throws Exception {
		// This is a public bucket
		BucketInfo info = service.bucketInfo("my-bucket");
		assertThat(info.name()).isEqualTo("my-bucket");
		assertThat(info.existsAndIsReadable()).isEqualTo(true);
	}

}
