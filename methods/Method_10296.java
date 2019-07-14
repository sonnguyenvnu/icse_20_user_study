/** 
 * If b.length is at least len, return b.  Otherwise return a new byte array of length len.
 */
private byte[] embiggen(byte[] b,int len){
  if (b == null || b.length < len) {
    return new byte[len];
  }
 else {
    return b;
  }
}
