/** 
 * Writes a Pascal String padded to the specified fixed size in bytes
 * @param s
 * @param length the fixed size in bytes
 * @throws java.io.IOException
 */
public void writePString(String s,int length) throws IOException {
  if (s.length() > length) {
    throw new IllegalArgumentException("String too long for PString of length " + length);
  }
  if (s.length() != 0 && s.length() < 256) {
    out.write(s.length());
  }
 else {
    out.write(0);
    writeShort(s.length());
  }
  for (int i=0; i < s.length(); i++) {
    out.write(s.charAt(i));
  }
  for (int i=1 + s.length(); i < length; i++) {
    out.write(0);
  }
  incCount(length);
}
