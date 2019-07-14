/** 
 * Writes the contents of the byte array into the specified output stream.
 * @param out
 */
public void toOutputStream(OutputStream out) throws IOException {
  out.write(buf,0,count);
}
