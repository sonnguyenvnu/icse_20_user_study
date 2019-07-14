/** 
 * Slow path in case a line of bytes cannot be read in one #fill() operation. This is still faster than creating the StrinbBuilder, String, then encoding as byte[] in Protocol, then decoding back into a String.
 */
private byte[] readLineBytesSlowly(){
  ByteArrayOutputStream bout=null;
  while (true) {
    ensureFill();
    byte b=buf[count++];
    if (b == '\r') {
      ensureFill();
      byte c=buf[count++];
      if (c == '\n') {
        break;
      }
      if (bout == null) {
        bout=new ByteArrayOutputStream(16);
      }
      bout.write(b);
      bout.write(c);
    }
 else {
      if (bout == null) {
        bout=new ByteArrayOutputStream(16);
      }
      bout.write(b);
    }
  }
  return bout == null ? new byte[0] : bout.toByteArray();
}
