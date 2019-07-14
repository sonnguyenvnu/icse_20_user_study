/** 
 * Detects optional BOM and reads UTF  {@link String} from an {@link InputStream}. If BOM is missing, UTF-8 is assumed.
 * @param inputStream {@link InputStream} to read.
 * @return String in UTF encoding.
 * @throws IOException if copy to {@link InputStream} errors.
 * @see #unicodeInputStreamOf(File)
 * @see StreamUtil#copy(InputStream,String)
 */
public static String readUTFString(final InputStream inputStream) throws IOException {
  UnicodeInputStream in=null;
  try {
    in=new UnicodeInputStream(inputStream,null);
    return StreamUtil.copy(in,detectEncoding(in)).toString();
  }
  finally {
    StreamUtil.close(in);
  }
}
