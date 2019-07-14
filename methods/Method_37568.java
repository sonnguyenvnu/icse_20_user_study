/** 
 * Downloads resource as String.
 */
public static String downloadString(final String url,final String encoding) throws IOException {
  try (InputStream inputStream=new URL(url).openStream()){
    return new String(StreamUtil.readChars(inputStream,encoding));
  }
 }
