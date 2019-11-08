package personal.kunj.junit5;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

class StringTest {

	@BeforeAll
	static void beforeAll() {
		System.out.println("Initialize connection to the database");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("Closing connection to the database");
	}

	// @Before --> In JUnit 4
	@BeforeEach
	void beforeEach(TestInfo info) {
		// TestInfo is not present in JUnit 4
		System.out.println("initializing data for each test" + info.getDisplayName());
	}

	// @After --> In JUnit 4
	@AfterEach
	void afterEach(TestInfo info) {
		// info.getDisplayName() will display the method name for all the tests
		System.out.println("clean up" + info.getDisplayName());
	}

	@Test
	void length_basic() {
		int actualLength = "ABCD".length();
		int expectedLength = 4;
		assertEquals(expectedLength, actualLength);
	}

	@Test
	void length_exception() {
		String str = null;
		Assertions.assertThrows(NullPointerException.class, () -> {
			str.length();
		});
	}

	@Test
	void toUpperCase() {
		String actualString = "kunj".toUpperCase();
		String expectedString = "KUNJ";
		assertNotNull(actualString);
		assertEquals(expectedString, actualString);
	}

	// Assserting boolean
	@Test
	void contains_basic() {
		String str = "abcdef";
		boolean result = str.contains("try");
		assertEquals(false, result); // same as assertFalse(result);
		// assertTrue
	}

	// inline version of the prev test
	@Test
	void contains_basic_innline() {
		assertFalse("abcdef".contains("try"));
	}

	@Test
	void array_split() {
		String str = "abc def ghi";
		String actualResult[] = str.split(" ");
		String expectedResult[] = new String[] { "abc", "def", "ghi" };
		assertArrayEquals(expectedResult, actualResult);
	}
}
