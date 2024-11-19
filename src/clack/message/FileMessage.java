package clack.message;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class FileMessage extends Message
{
    private final String fileContents;
    private final String fileName;

    public FileMessage(String username, String fileReadPath) throws IOException
    {
        this(username, fileReadPath, Paths.get(fileReadPath).getFileName().toString());
    }

    public FileMessage(String username, String fileReadPath, String fileSaveName) throws IOException
    {
        super(username, MsgTypeEnum.FILE);
        this.fileName = fileSaveName;
        this.fileContents = "";
        // this.fileContents = new String(Files.readAllBytes(Paths.get(fileReadPath)));
    }

    public String getFileContents() {
        return fileContents;
    }

    public String getFileName() {
        return fileName;
    }

}
