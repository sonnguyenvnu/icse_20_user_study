/** 
 * Reads all characters in this stream until this delimiting character has been encountered, taking into account this escape character for the delimiting character.
 * @param delimiter  The delimiting character.
 * @param selfEscape Whether the delimiter can escape itself by being present twice.
 * @return The string read, without the delimiting character.
 */
public String readUntilExcludingWithEscape(char delimiter,boolean selfEscape) throws IOException {
  return readUntilExcludingWithEscape(delimiter,selfEscape,(char)0);
}
