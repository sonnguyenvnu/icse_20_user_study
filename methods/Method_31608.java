/** 
 * Reads all characters in this stream as long as they can be part of a numeric constant.
 * @return The string read.
 */
public String readNumeric() throws IOException {
  StringBuilder result=new StringBuilder();
  do {
    if (peekNumeric()) {
      result.append((char)read());
    }
 else {
      break;
    }
  }
 while (true);
  return result.toString();
}
