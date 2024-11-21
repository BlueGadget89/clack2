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
        // this.fileContents = "";
        this.fileContents = new String(Files.readAllBytes(Paths.get(fileReadPath)));
    }

    public String getFileContents() {
        return fileContents;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || o.getClass() != this.getClass()) { return false; }
       // if (!super.equals(o)) return false;
        FileMessage that = (FileMessage) o;
        return Objects.equals(this.getFileContents(), that.getFileContents()) &&
                Objects.equals(this.getFileName(), that.getFileName());
    }

    @Override
    public String toString()
    {
        return "FileMessage{" +
                super.toString() +
                ", fileName='" + fileName + '\'' +
                ", fileContents='" + fileContents + '\'' +
                '}';
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), fileContents, fileName);
    }

}
