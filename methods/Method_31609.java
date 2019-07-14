/** 
 * Reads all characters in this stream as long as they are whitespace.
 * @return The string read.
 */
public String readWhitespace() throws IOException {
  StringBuilder result=new StringBuilder();
  do {
    if (peekWhitespace()) {
      result.append((char)read());
    }
 else {
      break;
    }
  }
 while (true);
  return result.toString();
}
