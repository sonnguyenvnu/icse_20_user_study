/** 
 * Get the next character.
 * @return The next character.
 * @throws ParseException If there were no more characters in the string.
 */
public char getc() throws ParseException {
  if (position >= string.length()) {
    throw new ParseException(this,"Ran out of input while parsing");
  }
  return string.charAt(position++);
}
