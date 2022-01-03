import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CoffeeDecoratorTest {
	Coffee coffee = new BasicCoffee();

	@BeforeEach
	void init(){
		coffee = new BasicCoffee();
	}

	@Test
	void null_1Test() {
		assertNotNull(coffee, "Wrong answer");
	}

	@Test
	void null_2Test() {
		assertNotNull(coffee.getInitialCost(), "Wrong answer");
	}

	@Test
	void null_3Test() {
		assertNotNull(coffee.makeCoffee(), "Wrong answer");
	}

	@Test
	void price_1Test() {
		assertNotNull(coffee, "Wrong answer");
	}

	@Test
	void price_2Test() {
		assertEquals(coffee.getInitialCost(), 3.99, "Wrong answer");
	}

	@Test
	void price_3Test() {
		coffee = new Cream(coffee);
		assertEquals(coffee.makeCoffee(), 4.49, "Wrong answer");
	}

	@Test
	void price_4Test() {
		coffee = new Cream(coffee);
		assertEquals(coffee.makeCoffee(), 4.49, "Wrong answer");
	}

	@Test
	void price_5Test() {
		coffee = new ExtraShot(coffee);
		assertEquals(coffee.makeCoffee(), 5.19, "Wrong answer");
	}

	@Test
	void price_6Test() {
		coffee = new Cream(new PumpkinSpice(new ExtraShot(coffee)));
		assertTrue(Math.abs(coffee.makeCoffee() - 6.39)< 0.01, "Wrong answer");
	}
	@Test
	void price_7Test() {
		coffee = new Caramel(coffee);
		coffee = new Sugar(coffee);
		coffee = new PumpkinSpice(coffee);
		coffee = new Cream(coffee);
		coffee = new ExtraShot(coffee);
		assertEquals(coffee.makeCoffee(), 7.49, "wrong answer");
	}


}
