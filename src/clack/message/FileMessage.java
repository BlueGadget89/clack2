package clack.message;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Represents a message that includes a file in the Clack system.
 * This class extends the Message class to include file-specific information, like name
 * and contents of a file.
 */
public class FileMessage extends Message
{
    private final String fileContents;
    private final String fileName;

    /**
     * Constructs a FileMessage with the given username and file path.
     * The file name is derived from the file path.
     *
     * @param username     the username of the user sending the message
     * @param fileReadPath the path to the file to be read
     * @throws IOException if there is an error reading the file
     */
    public FileMessage(String username, String fileReadPath) throws IOException
    {
        this(username, fileReadPath, Paths.get(fileReadPath).getFileName().toString());
    }

    /**
     * Constructs a FileMessage with the given username, file path, and save-as name.
     *
     * @param username     the username of the user sending the message
     * @param fileReadPath the path to the file to be read
     * @param fileSaveName the name to save the file as
     * @throws IOException if there is an error reading the file
     */
    public FileMessage(String username, String fileReadPath, String fileSaveName) throws IOException
    {
        super(username, MsgTypeEnum.FILE);
        this.fileName = Paths.get(fileSaveName).getFileName().toString(); // Store only the file name
        this.fileContents = new String(Files.readAllBytes(Paths.get(fileReadPath)));
    }

    /**
     * Gets the contents of the file.
     *
     * @return the contents of the file as a String
     */
    public String getFileContents() {
        return fileContents;
    }

    /**
     * Gets the name of the file.
     *
     * @return the name of the file
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Compares this FileMessage to another object for equality.
     *
     * @param o the object to compare with
     * @return true if this object is equal to o, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || o.getClass() != this.getClass()) { return false; }
       // if (!super.equals(o)) return false;
        FileMessage that = (FileMessage) o;
        return Objects.equals(this.getFileContents(), that.getFileContents()) &&
                Objects.equals(this.getFileName(), that.getFileName());
    }

    /**
     * Returns a string representation of this FileMessage.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString()
    {
        return "FileMessage{" +
                super.toString() +
                ", fileName='" + fileName + '\'' +
                ", fileContents='" + fileContents + '\'' +
                '}';
    }

    /**
     * Returns a hash code value for this FileMessage.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), fileContents, fileName);
    }

}
