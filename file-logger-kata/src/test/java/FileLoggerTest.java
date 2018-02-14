import org.junit.Test;
import org.junit.Assert;

public class FileLoggerTest {
    @Test
    public void test_class_existence(){
        try {
            Class.forName("FileLogger");
        } catch(ClassNotFoundException e) {
            Assert.fail("Class named FileLogger should exist");
        }
    }
}
