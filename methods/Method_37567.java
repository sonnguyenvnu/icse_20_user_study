/** 
 * Downloads resource as byte array.
 */
public static byte[] downloadBytes(final String url) throws IOException {
  try (InputStream inputStream=new URL(url).openStream()){
    return StreamUtil.readBytes(inputStream);
  }
 }
