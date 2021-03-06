import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FileLoggerTest {

    @Test
    public void test_file_creation() throws IOException {
        // Context
        FileLogger logger = new FileLogger();
        Path path = Paths.get(logger.getFileName());
        Files.deleteIfExists(path);
        Assert.assertFalse(Files.exists(path));

        // Business logic
        logger.log("Hello");

        // Asserts
        Assert.assertTrue(Files.exists(path));
    }

    @Test
    public void test_if_message_is_appended() throws IOException {
        // Context
        FileLogger logger = new FileLogger();
        Path path = Paths.get(logger.getFileName());
        String message = "My Message";
        logger.log("hello");
        logger.log("world");

        // Business logic
        List<String> linesBefore = Files.readAllLines(path);
        logger.log(message);
        List<String> linesAfter = Files.readAllLines(path);

        // Asserts
        for(int i = 0; i < linesBefore.size(); ++i) {
           Assert.assertEquals(linesBefore.get(i), linesAfter.get(i));
        }
        Assert.assertEquals(linesBefore.size() + 1, linesAfter.size());
        Assert.assertEquals(message, linesAfter.get(linesAfter.size() - 1));
    }

    @Test
    public void test_if_name_is_correct() throws IOException {
        // Context
        LocalDate date = LocalDate.of(2018,02,14);
        FileLogger fileLogger = new FileLogger(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String strDate = date.format(formatter);
        String expectedFilename = "log" + strDate + ".txt";

        // Business logic
        String filename = fileLogger.getFileName();

        // Asserts
        Assert.assertEquals(expectedFilename, filename);
    }

    @Test
    public void test_file_name_on_saturday()
    {
        // Context
        LocalDate date = LocalDate.of(2018,02,17);
        FileLogger fileLogger = new FileLogger(date);
        String expectedFilename = "weekend.txt";

        // Business logic
        String filename = fileLogger.getFileName();

        // Asserts
        Assert.assertEquals(expectedFilename, filename);
    }

    @Test
    public void test_file_name_on_sunday()
    {
        // Context
        LocalDate date = LocalDate.of(2018,02,18);
        FileLogger fileLogger = new FileLogger(date);
        String expectedFilename = "weekend.txt";

        // Business logic
        String filename = fileLogger.getFileName();

        // Asserts
        Assert.assertEquals(expectedFilename, filename);
    }
}
