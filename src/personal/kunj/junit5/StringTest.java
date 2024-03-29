package personal.kunj.junit5;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringTest {

	private String str;

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
	void length_greater_than_zero() {
		assertTrue("ABCD".length() > 0);
		assertTrue("A".length() > 0);
		assertTrue("AB".length() > 0);
		assertTrue("ABC".length() > 0);
	}

	@ParameterizedTest
	@ValueSource(strings = { "ABCD", "A", "AB", "ABC" })
	void length_greater_than_zero_parameterized_tests(String str) {
		assertTrue(str.length() > 0);
	}

	@ParameterizedTest(name = "{0} toUpperCase is {1}")
	@CsvSource(value = { "abcd, ABCD", "abc, ABC", "'',''", "abcdefg, ABCDEFG" })
	void uppercase(String word, String capitalizedWord) {
		assertEquals(capitalizedWord, word.toUpperCase());
	}

	@ParameterizedTest(name = "{0} length is {1}")
	@CsvSource(value = { "abcd, 4", "abc, 3", "'',0", "abcdefg, 7" })
	void length(String word, int length) {
		assertEquals(length, word.length());
	}

	@Test
	@DisplayName("When length is null, throw an exception")
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
	@RepeatedTest(10) // In JUnit 5
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

	@Test
	@Disabled // @Ignored in Junit4
	void performanceTest() {
		Assertions.assertTimeout(Duration.ofSeconds(5), () -> {
			for (int i = 0; i <= 1000000; i++) {
				int j = i;
				System.out.println(j);
			}
		}

		);
	}

	@Nested
	@DisplayName("For an empty String")
	class EmptyStringTests {

		@BeforeEach
		void setToEmpty() {
			str = "";
		}

		@Test
		@DisplayName("length should be zero")
		void lengthIsZero() {
			assertEquals(0, str.length());
		}

		@Test
		@DisplayName("upper case is empty")
		void uppercaseIsEmpty() {
			assertEquals("", str.toUpperCase());
		}

	}

	@Nested
	@DisplayName("For large strings")
	class LargeStringTests {

		@BeforeEach
		void setToALargeString() {
			str = "dlkfjlajbvjcxzbnhrewu";
		}

		@Test
		void lengthIsGreaterThanZero() {
			assertEquals(21, str.length());
		}

	}

}
