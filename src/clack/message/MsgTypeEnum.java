/**
 * Enumerates the possible types of messages in the Clack system.
 * <p>
 * This enum is used to identify the type of a message.
 * <p>
 * There are seven message types:
 * <ul>
 * <li> LOGIN - Messages that are used to log in to the Clack system.
 * <li> FILE - Messages that contain a file.
 * <li> HELP - Messages that request help information.
 * <li> OPTION - Messages that request the value of an option.
 * <li> LISTUSERS - Messages that request the list of users in the Clack system.
 * <li> LOGOUT - Messages that are used to log out of the Clack system.
 * <li> TEXT - Regular text messages.
 * </ul>
 * <p>
 *
 */
package clack.message;

public enum MsgTypeEnum
{
    LOGIN,
    FILE,
    HELP,
    OPTION,
    LISTUSERS,
    LOGOUT,
    TEXT
}
