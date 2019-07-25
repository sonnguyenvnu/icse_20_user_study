/** 
 * Algorythm implementation.
 * @param is {@code InputStream} of file to calculate checksum
 * @return checksum string
 */
@Override public String apply(InputStream is) throws Exception {
  byte[] buffer=new byte[BUFFER_SIZE];
  Checksum checksum=new Adler32();
  int read=is.read(buffer,0,BUFFER_SIZE);
  while (read != -1) {
    checksum.update(buffer,0,read);
    read=is.read(buffer,0,BUFFER_SIZE);
  }
  return Long.toHexString(checksum.getValue());
}
