package personal.kunj.junit5;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StringTest {

	@Test
	void length_basic() {
		int actualLength = "ABCD".length();
		int expectedLength = 4;
		assertEquals(expectedLength, actualLength);
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
