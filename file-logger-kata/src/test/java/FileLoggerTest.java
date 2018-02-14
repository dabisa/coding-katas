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

    @Test
    public void test_method_existence(){
        try {
            FileLogger.class.getDeclaredMethod("log", String.class);
        } catch(NoSuchMethodException e) {
            Assert.fail("Class named FileLogger should have method called log with string parameter");
        }
    }
}
