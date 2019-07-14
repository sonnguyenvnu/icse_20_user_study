/** 
 * Reads all characters in this stream as long as they can be part of a keyword.
 * @param delimiter The current delimiter.
 * @return The string read.
 */
public String readKeywordPart(Delimiter delimiter) throws IOException {
  StringBuilder result=new StringBuilder();
  do {
    if ((delimiter == null || !peek(delimiter.getDelimiter())) && peekKeywordPart()) {
      result.append((char)read());
    }
 else {
      break;
    }
  }
 while (true);
  return result.toString();
}
