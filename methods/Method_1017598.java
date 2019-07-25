/** 
 * Decode an array of bytes into a string.
 * @param encodedString a byte array containing the string to decode
 * @param offset        the offset in <code>encodedString</code> of the first byte of the encodedrepresentation
 * @param length        the length, in bytes, of the encoded representation
 * @return the decoded string
 * @throws IOException if something goes wrong
 */
public String decode(byte[] encodedString,int offset,int length) throws IOException {
  return new String(encodedString,offset,length,encoding);
}
