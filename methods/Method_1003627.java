/** 
 * Algorythm implementation.
 * @param is {@code InputStream} of file to calculate checksum
 * @return checksum string
 */
@Override public String apply(InputStream is) throws Exception {
  byte[] buffer=new byte[BUFFER_SIZE];
  MessageDigest md=MessageDigest.getInstance("MD5");
  int read=is.read(buffer,0,BUFFER_SIZE);
  while (read != -1) {
    md.update(buffer,0,read);
    read=is.read(buffer,0,BUFFER_SIZE);
  }
  byte[] digest=md.digest();
  StringBuilder sb=new StringBuilder();
  for (int i=0; i < digest.length; i++) {
    sb.append(Integer.toString((digest[i] & 0xFF) + 0x100,16).substring(1));
  }
  return sb.toString();
}
