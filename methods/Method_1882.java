/** 
 * This method checks for the dimension of the WebP image from the given InputStream. We don't support mark/reset and the Stream is always closed.
 * @param is The InputStream used for read WebP data
 * @return The Size of the WebP image if any or null if the size is not available
 */
@Nullable public static Pair<Integer,Integer> getSize(InputStream is){
  Pair<Integer,Integer> result=null;
  byte[] headerBuffer=new byte[4];
  try {
    is.read(headerBuffer);
    if (!compare(headerBuffer,"RIFF")) {
      return null;
    }
    getInt(is);
    is.read(headerBuffer);
    if (!compare(headerBuffer,"WEBP")) {
      return null;
    }
    is.read(headerBuffer);
    final String headerAsString=getHeader(headerBuffer);
    if (VP8_HEADER.equals(headerAsString)) {
      return getVP8Dimension(is);
    }
 else     if (VP8L_HEADER.equals(headerAsString)) {
      return getVP8LDimension(is);
    }
 else     if (VP8X_HEADER.equals(headerAsString)) {
      return getVP8XDimension(is);
    }
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
 finally {
    if (is != null) {
      try {
        is.close();
      }
 catch (      IOException e) {
        e.printStackTrace();
      }
    }
  }
  return null;
}
