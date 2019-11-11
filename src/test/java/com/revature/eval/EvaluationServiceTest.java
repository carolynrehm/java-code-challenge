package com.revature.eval;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.eval.EvaluationService;

public class EvaluationServiceTest {
	
	private static final EvaluationService evaluationService = new EvaluationService();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/*******************************************************************
	 * Question 1
	 ******************************************************************/
	
	@Test
	public void reverseNullString() {
		assertNull(evaluationService.reverse(null));
	}
	
	@Test
	public void testAnEmptyString() {
		assertEquals("", evaluationService.reverse(""));
	}

	@Test
	public void testAWord() {
		assertEquals("tobor", evaluationService.reverse("robot"));
	}

	@Test
	public void testACapitalizedWord() {
		assertEquals("nemaR", evaluationService.reverse("Ramen"));
	}

	@Test
	public void testASentenceWithPunctuation() {
		assertEquals("!yrgnuh m'I", evaluationService.reverse("I'm hungry!"));
	}

	@Test
	public void testAPalindrome() {
		assertEquals("racecar", evaluationService.reverse("racecar"));
	}
	
	/*******************************************************************
	 * Question 2
	 ******************************************************************/
	
	@Test
	public void testNullPhrase() {
		final String phrase = null;
		assertNull(evaluationService.acronym(phrase));
	}
	
	@Test
	public void basic() {
		final String phrase = "Portable Network Graphics";
		final String expected = "PNG";
		assertEquals(expected, evaluationService.acronym(phrase));
	}

	@Test
	public void punctuation() {
		final String phrase = "First In, First Out";
		final String expected = "FIFO";
		assertEquals(expected, evaluationService.acronym(phrase));
	}

	@Test
	public void NonAcronymAllCapsWord() {
		final String phrase = "GNU Image Manipulation Program";
		final String expected = "GIMP";
		assertEquals(expected, evaluationService.acronym(phrase));
	}

	@Test
	public void punctuationWithoutWhitespace() {
		final String phrase = "Complementary metal-oxide semiconductor";
		final String expected = "CMOS";
		assertEquals(expected, evaluationService.acronym(phrase));
	}
	
	/*******************************************************************
	 * Question 3
	 ******************************************************************/
	@Test
	public void testAValuableLetter() {
		assertEquals(4, evaluationService.getScrabbleScore("f"));
	}

	@Test
	public void testAShortValuableWord() {
		assertEquals(12, evaluationService.getScrabbleScore("zoo"));
	}

	@Test
	public void testAMediumWord() {
		assertEquals(6, evaluationService.getScrabbleScore("street"));
	}

	@Test
	public void testAMediumValuableWord() {
		assertEquals(22, evaluationService.getScrabbleScore("quirky"));
	}

	@Test
	public void testALongMixCaseWord() {
		assertEquals(41, evaluationService.getScrabbleScore("OxyphenButazone"));
	}
	
	/*******************************************************************
	 * Question 4
	 ******************************************************************/
	@Test
	public void countOneWord() {
		Map<String, Integer> expectedWordCount = new HashMap<>();
		expectedWordCount.put("word", 1);

		Map<String, Integer> actualWordCount = evaluationService.wordCount("word");
		assertEquals(expectedWordCount, actualWordCount);
	}

	@Test
	public void countOneOfEachWord() {
		Map<String, Integer> expectedWordCount = new HashMap<>();
		expectedWordCount.put("one", 1);
		expectedWordCount.put("of", 1);
		expectedWordCount.put("each", 1);
 
		Map<String, Integer> actualWordCount = evaluationService.wordCount("one of each");
		assertEquals(expectedWordCount, actualWordCount);
	}

	@Test
	public void multipleOccurrencesOfAWord() {
		Map<String, Integer> expectedWordCount = new HashMap<>();
		expectedWordCount.put("one", 1);
		expectedWordCount.put("fish", 4);
		expectedWordCount.put("two", 1);
		expectedWordCount.put("red", 1);
		expectedWordCount.put("blue", 1);

		Map<String, Integer> actualWordCount = evaluationService.wordCount("one fish two fish red fish blue fish");
		assertEquals(expectedWordCount, actualWordCount);
	}

	@Test
	public void handlesCrampedLists() {
		Map<String, Integer> expectedWordCount = new HashMap<>();
		expectedWordCount.put("one", 1);
		expectedWordCount.put("two", 1);
		expectedWordCount.put("three", 1);

		Map<String, Integer> actualWordCount = evaluationService.wordCount("one,two,three");
		assertEquals(expectedWordCount, actualWordCount);
	}

	@Test
	public void handlesExpandedLists() {
		Map<String, Integer> expectedWordCount = new HashMap<>();
		expectedWordCount.put("one", 1);
		expectedWordCount.put("two", 1);
		expectedWordCount.put("three", 1);

		Map<String, Integer> actualWordCount = evaluationService.wordCount("one,\ntwo,\nthree");
		assertEquals(expectedWordCount, actualWordCount);
	}
	
	/*******************************************************************
	 * Question 5
	 ******************************************************************/
	
	@Test
	public void findsAValueInTheMiddleOfAnArray() {
		List<String> sortedList = Collections.unmodifiableList(Arrays.asList("1", "3", "4", "6", "8", "9", "11"));

		EvaluationService.BinarySearch<String> search = new EvaluationService.BinarySearch<>(sortedList);

		assertEquals(3, search.indexOf("6"));
	}

	@Test
	public void findsAValueAtTheBeginningOfAnArray() {
		List<Integer> sortedList = Collections.unmodifiableList(Arrays.asList(1, 3, 4, 6, 8, 9, 11));

		EvaluationService.BinarySearch<Integer> search = new EvaluationService.BinarySearch<>(sortedList);

		assertEquals(0, search.indexOf(1));
	}

	@Test
	public void findsAValueAtTheEndOfAnArray() {
		List<Integer> sortedList = Collections.unmodifiableList(Arrays.asList(1, 3, 4, 6, 8, 9, 11));

		EvaluationService.BinarySearch<Integer> search = new EvaluationService.BinarySearch<>(sortedList);

		assertEquals(6, search.indexOf(11));
	}

	@Test
	public void findsAValueInAnArrayOfOddLength() {
		List<Integer> sortedListOfOddLength = Collections
				.unmodifiableList(Arrays.asList(1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 634));

		EvaluationService.BinarySearch<Integer> search = new EvaluationService.BinarySearch<>(sortedListOfOddLength);

		assertEquals(9, search.indexOf(144));
	}

	@Test
	public void findsAValueInAnArrayOfEvenLength() {
		List<Integer> sortedListOfEvenLength = Collections
				.unmodifiableList(Arrays.asList(1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377));

		EvaluationService.BinarySearch<Integer> search = new EvaluationService.BinarySearch<>(sortedListOfEvenLength);

		assertEquals(5, search.indexOf(21));
	}
	
}
