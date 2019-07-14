/** 
 * Detects optional BOM and reads UTF  {@link String} from a {@link File}. If BOM is missing, UTF-8 is assumed.
 * @param file {@link File} to read.
 * @return String in UTF encoding.
 * @throws IOException if copy to {@link InputStream} errors.
 * @see #unicodeInputStreamOf(File)
 * @see StreamUtil#copy(InputStream,String)
 */
public static String readUTFString(final File file) throws IOException {
  UnicodeInputStream in=unicodeInputStreamOf(file);
  try {
    return StreamUtil.copy(in,detectEncoding(in)).toString();
  }
  finally {
    StreamUtil.close(in);
  }
}
