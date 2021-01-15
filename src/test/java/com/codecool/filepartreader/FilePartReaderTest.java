package test.java.com.codecool.filepartreader;

import main.java.com.codecool.filepartreader.FilePartReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {
    FilePartReader reader;

    @BeforeEach
    void setReader() {
        reader = new FilePartReader();
        reader.setup("src/test/java/com/codecool/filepartreader/text.txt", 1, 1);
    }

    @AfterEach
    void destroyReader() {
        reader = null;
    }

    @Test
    void should_ReturnFileContent_ForTestFile() throws IOException {
        String fileContent = reader.read();
        assertEquals("Line 1 some more words for sorting Rotator\nLine 2\nLine 3\nLine 4 deified, civic, radar, level, rotor, kayak, reviver", fileContent, "The read method should return all file lines as String");
    }

    @Test
    void should_ReturnGivenLinesOfFile_ForTestFile() {
        assertAll(
                () -> assertEquals("Line 1 some more words for sorting Rotator\n", reader.readLines()),
                () -> {
                    reader.setup("src/test/java/com/codecool/filepartreader/text.txt", 2, 2);
                    assertEquals("Line 2\n", reader.readLines());
                },
                () -> {
                    reader.setup("src/test/java/com/codecool/filepartreader/text.txt", 1, 4);
                    assertEquals("Line 1 some more words for sorting Rotator\n" +
                            "Line 2\n" +
                            "Line 3\n" +
                            "Line 4 deified, civic, radar, level, rotor, kayak, reviver\n", reader.readLines());
                },
                () -> {
                    reader.setup("src/test/java/com/codecool/filepartreader/text.txt", 1, 10);
                    assertEquals("Line 1 some more words for sorting Rotator\n" +
                            "Line 2\n" +
                            "Line 3\n" +
                            "Line 4 deified, civic, radar, level, rotor, kayak, reviver\n", reader.readLines());
                }
        );
    }

    @Test
    void should_ThrowIOException_ForWrongFileName() {
        reader.setup("src/test/java/com/codecool/filepartreader/a.txt", 1, 1);

        assertThrows(IOException.class, () -> {
            reader.read();
        });
    }

    @Test
    void should_ThrowIllegalArgumentException_ForNegativeFromLine() {
        assertThrows(IllegalArgumentException.class, () -> reader.setup("src/test/java/com/codecool/filepartreader/text.txt", -1, 1));
    }

    @Test
    void should_ThrowIllegalArgumentException_ForFromLineGreaterThanToLine() {
        assertThrows(IllegalArgumentException.class, () -> reader.setup("src/test/java/com/codecool/filepartreader/text.txt", 2, 1));
    }
}
