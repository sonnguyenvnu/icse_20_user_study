/** 
 * Downloads resource as String.
 */
public static String downloadString(final String url) throws IOException {
  try (InputStream inputStream=new URL(url).openStream()){
    return new String(StreamUtil.readChars(inputStream));
  }
 }
