/** 
 * @nowebref
 */
static public byte[] loadBytes(InputStream input){
  try {
    ByteArrayOutputStream out=new ByteArrayOutputStream();
    byte[] buffer=new byte[4096];
    int bytesRead=input.read(buffer);
    while (bytesRead != -1) {
      out.write(buffer,0,bytesRead);
      bytesRead=input.read(buffer);
    }
    out.flush();
    return out.toByteArray();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return null;
}
