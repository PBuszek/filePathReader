package test.java.com.codecool.filepartreader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileWordAnalyzerTest {
    @Mock
    static FilePartReader reader;

    @InjectMocks
    static FileWordAnalyzer analyzer;

    @BeforeEach
    void setAnalyzer() throws IOException {
        when(reader.readLines()).thenReturn(
                "Line 1 some more words for sorting Rotator\n",
                "Line 4 deified, civic, radar, level, rotor, kayak, reviver",
                "Line 1 some more words for sorting Rotator\n" +
                        "Line 2\n" +
                        "Line 3\n" +
                        "Line 4 deified, civic, radar, level, rotor, kayak, reviver\n");
        analyzer = new FileWordAnalyzer(reader);
    }

    @AfterEach
    void destroyAnalyzer() {
        analyzer = null;
    }

    @Test
    void should_ReturnWordsOrderedAlphabetically_ForTestFile() {
        var words = Arrays.asList("1", "for", "Line", "more", "Rotator", "some", "sorting", "words");
        var words2 = Arrays.asList("4", "civic", "deified", "kayak", "level", "Line", "radar", "reviver", "rotor");
        var words3 = Arrays.asList("1", "2", "3", "4", "civic", "deified", "for", "kayak", "level", "Line", "Line", "Line", "Line", "more", "radar", "reviver", "Rotator", "rotor", "some", "sorting", "words");
        assertAll(
                () -> assertEquals(words, analyzer.getWordsOrderedAlphabetically()),
                () -> assertEquals(words2, analyzer.getWordsOrderedAlphabetically()),
                () -> assertEquals(words3, analyzer.getWordsOrderedAlphabetically()));
    }

    @Test
    void should_ReturnPalindromes_ForTestFile() {
        var words = Arrays.asList("1", "Rotator");
        var words2 = Arrays.asList("4", "deified", "civic", "radar", "level", "rotor", "kayak", "reviver");
        var words3 = Arrays.asList("1", "Rotator", "2", "3", "4", "deified", "civic", "radar", "level", "rotor", "kayak", "reviver");
        assertAll(
                () -> assertEquals(words, analyzer.getStringsWhichPalindromes()),
                () -> assertEquals(words2, analyzer.getStringsWhichPalindromes()),
                () -> assertEquals(words3, analyzer.getStringsWhichPalindromes()));
    }

    @Test
    void should_ReturnWordsContainingSubstring_ForTestFile() {
        var containingSo = Arrays.asList("some", "sorting");
        var containingSo2 = Collections.emptyList();
        var containingOr = Arrays.asList("more", "words", "for", "sorting", "Rotator", "rotor");
        assertAll(
                () -> assertEquals(containingSo, analyzer.getWordsContainingSubstring("so")),
                () -> assertEquals(containingSo2, analyzer.getWordsContainingSubstring("so")),
                () -> assertEquals(containingOr, analyzer.getWordsContainingSubstring("or")));
    }
}
