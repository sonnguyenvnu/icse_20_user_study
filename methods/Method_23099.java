/** 
 * Writes a signed 16 bit integer value.
 * @param v The value
 * @throws java.io.IOException
 */
public void writeShort(int v) throws IOException {
  out.write((v >> 8) & 0xff);
  out.write((v >>> 0) & 0xff);
  incCount(2);
}
