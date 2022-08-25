package co.com.home.labs.springboot.store.shopping;

import co.com.home.labs.springboot.store.shopping.client.CustomerClient;
import co.com.home.labs.springboot.store.shopping.client.ProductClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ShoppingServiceApplicationTests {
	// Mock this to avoid the dependency without being loaded
	@MockBean
	private CustomerClient customerClient;

	// Mock this to avoid the dependency without being loaded
	@MockBean
	private ProductClient productClient;

	@Test
	public void contextLoads() {
	}

}
