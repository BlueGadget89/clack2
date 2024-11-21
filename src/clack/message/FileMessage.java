package clack.message;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @param username
 * @param filepath
 * @param fileSaveAsName
 * @throws IOException
 */
public class FileMessage extends Message {

    private String fileContents;
    private String fileName;

    public FileMessage(String username, String filepath, String fileSaveAsName) throws IOException {
        super(username, MsgTypeEnum.FILE);
        this.fileName = String.valueOf(Path.of(fileSaveAsName).getFileName());
        this.fileContents = Files.readString(Path.of(filepath));
    }

    public FileMessage(String username, String filepath) throws IOException {
       this(username, filepath, filepath);

    }

    @Override
    public String toString()
    {
        return "FileMessage{"
                + super.toString()
                + ",text='" + fileContents + '\'' +
                '}';
    }
}
