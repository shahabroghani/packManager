package com.example.packmanager;

import com.example.packmanager.entity.Product;
import com.example.packmanager.util.PackUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootTest
class PackManagerApplicationTests {

	@Test
	void packTest1() {
		Product product1 = new Product(1L, BigDecimal.valueOf(23.9),BigDecimal.valueOf(45));
		Product product2 = new Product(2L, BigDecimal.valueOf(80.5),BigDecimal.valueOf(83));
		Product product3 = new Product(3L, BigDecimal.valueOf(62.48),BigDecimal.valueOf(53));

		List<Product> products = new ArrayList<>(List.of(product1, product2, product3));
		BigDecimal maxWeight = BigDecimal.valueOf(75);

		PackUtil packUtil = new PackUtil(products, maxWeight);

		List<Product> actual = packUtil.pack();

		List<Product> expected = new ArrayList<>(List.of(product3));

		Assert.isTrue(actual.size() == expected.size() && actual.containsAll(expected) && expected.containsAll(actual), "assertion failed");
	}

	@Test
	void packTest2() {
		Product product1 = new Product(1L, BigDecimal.valueOf(53.38),BigDecimal.valueOf(45));
		Product product2 = new Product(2L, BigDecimal.valueOf(88.62),BigDecimal.valueOf(98));
		Product product3 = new Product(3L, BigDecimal.valueOf(78.48),BigDecimal.valueOf(3));
		Product product4 = new Product(4L, BigDecimal.valueOf(72.30),BigDecimal.valueOf(76));
		Product product5 = new Product(5L, BigDecimal.valueOf(30.18),BigDecimal.valueOf(9));
		Product product6 = new Product(6L, BigDecimal.valueOf(46.34),BigDecimal.valueOf(48));

		List<Product> products = new ArrayList<>(List.of(product1, product2, product3, product4, product5, product6));
		BigDecimal maxWeight = BigDecimal.valueOf(81);

		PackUtil packUtil = new PackUtil(products, maxWeight);

		List<Product> actual = packUtil.pack();

		List<Product> expected = new ArrayList<>(List.of(product4));

		Assert.isTrue(actual.size() == expected.size() && actual.containsAll(expected) && expected.containsAll(actual), "assertion failed");
	}

	@Test
	void packTest3() {
		Product product1 = new Product(1L, BigDecimal.valueOf(15.3),BigDecimal.valueOf(34));

		List<Product> products = new ArrayList<>(List.of(product1));
		BigDecimal maxWeight = BigDecimal.valueOf(8);

		PackUtil packUtil = new PackUtil(products, maxWeight);

		List<Product> actual = packUtil.pack();

		List<Product> expected = new ArrayList<>();

		Assert.isTrue(actual.size() == expected.size() && actual.containsAll(expected) && expected.containsAll(actual), "assertion failed");
	}

	@Test
	void packTest4() {
		Product product1 = new Product(1L, BigDecimal.valueOf(85.31),BigDecimal.valueOf(29));
		Product product2 = new Product(2L, BigDecimal.valueOf(14.55),BigDecimal.valueOf(74));
		Product product3 = new Product(3L, BigDecimal.valueOf(3.98),BigDecimal.valueOf(16));
		Product product4 = new Product(4L, BigDecimal.valueOf(26.24),BigDecimal.valueOf(55));
		Product product5 = new Product(5L, BigDecimal.valueOf(63.69),BigDecimal.valueOf(52));
		Product product6 = new Product(6L, BigDecimal.valueOf(76.25),BigDecimal.valueOf(75));
		Product product7 = new Product(7L, BigDecimal.valueOf(60.02),BigDecimal.valueOf(74));
		Product product8 = new Product(8L, BigDecimal.valueOf(93.18),BigDecimal.valueOf(35));
		Product product9 = new Product(9L, BigDecimal.valueOf(89.95),BigDecimal.valueOf(64));

		List<Product> products = new ArrayList<>(List.of(product1, product2, product3, product4, product5, product6, product7, product8, product9));
		BigDecimal maxWeight = BigDecimal.valueOf(75);

		PackUtil packUtil = new PackUtil(products, maxWeight);

		List<Product> actual = packUtil.pack();

		List<Product> expected = new ArrayList<>(List.of(product2, product7));

		Assert.isTrue(actual.size() == expected.size() && actual.containsAll(expected) && expected.containsAll(actual), "assertion failed");
	}

	@Test
	void packTest5() {
		Product product1 = new Product(1L, BigDecimal.valueOf(90.72),BigDecimal.valueOf(13));
		Product product2 = new Product(2L, BigDecimal.valueOf(33.80),BigDecimal.valueOf(40));
		Product product3 = new Product(3L, BigDecimal.valueOf(43.15),BigDecimal.valueOf(10));
		Product product4 = new Product(4L, BigDecimal.valueOf(37.97),BigDecimal.valueOf(16));
		Product product5 = new Product(5L, BigDecimal.valueOf(46.81),BigDecimal.valueOf(36));
		Product product6 = new Product(6L, BigDecimal.valueOf(48.77),BigDecimal.valueOf(79));
		Product product7 = new Product(7L, BigDecimal.valueOf(81.80),BigDecimal.valueOf(45));
		Product product8 = new Product(8L, BigDecimal.valueOf(19.36),BigDecimal.valueOf(79));
		Product product9 = new Product(9L, BigDecimal.valueOf(6.76),BigDecimal.valueOf(64));

		List<Product> products = new ArrayList<>(List.of(product1, product2, product3, product4, product5, product6, product7, product8, product9));
		BigDecimal maxWeight = BigDecimal.valueOf(56);

		PackUtil packUtil = new PackUtil(products, maxWeight);

		List<Product> actual = packUtil.pack();

		List<Product> expected = new ArrayList<>(List.of(product6, product9));

		Assert.isTrue(actual.size() == expected.size() && actual.containsAll(expected) && expected.containsAll(actual), "assertion failed");
	}

}
