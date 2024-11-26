package clack.message;

/**
 * Enumerates the options that can be sent in an OptionMessage.
 * <ul>
 *     <li>CIPHER_KEY: sets the key for the cipher named in
 *         CIPHER_NAME.</li>
 *     <li>CIPHER_NAME: specifies the name of the cipher to use for
 *         communication with the server.</li>
 *     <li>CIPHER_ENABLE: enable or disable encryption of messages
 *         sent to the server. If disabled, all messages are sent
 *         in the clear.</li>
 * </ul>
 */
public enum OptionEnum {
    CIPHER_KEY,
    CIPHER_NAME,
    CIPHER_ENABLE
}
