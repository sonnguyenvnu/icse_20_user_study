/** 
 * Loads properties from string.
 */
public static void loadFromString(final Properties p,final String data) throws IOException {
  try (ByteArrayInputStream is=new ByteArrayInputStream(data.getBytes(StringPool.ISO_8859_1))){
    p.load(is);
  }
 }
