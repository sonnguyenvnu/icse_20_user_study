/** 
 * Swallows all characters in this stream until this delimiting character has been encountered, taking into account this escape character for the delimiting character.
 * @param delimiter  The delimiting character.
 * @param selfEscape Whether the delimiter can escape itself by being present twice.
 */
public void swallowUntilExcludingWithEscape(char delimiter,boolean selfEscape) throws IOException {
  swallowUntilExcludingWithEscape(delimiter,selfEscape,(char)0);
}
