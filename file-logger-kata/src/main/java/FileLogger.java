import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileLogger{

    public FileLogger() {
        localDate = LocalDate.now();
    }

    public FileLogger(LocalDate date) {
        localDate = date;
    }

    public String getFileName() {
        switch(localDate.getDayOfWeek()) {
            case SATURDAY:
            case SUNDAY:
                return "weekend.txt";
            default:
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                String strDate = localDate.format(formatter);
                return "log" + strDate + ".txt";
        }
    }

    public void log(String message) throws IOException {
        String filename = getFileName();
        File file = new File(filename);
        file.createNewFile();
        Writer writer = new FileWriter(file, true);
        writer.append(message + "\n");
        writer.flush();
    }

    private LocalDate localDate;
}
