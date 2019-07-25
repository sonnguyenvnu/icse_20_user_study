/** 
 * read string.
 * @param reader Reader instance.
 * @return String.
 * @throws IOException
 */
public static String read(Reader reader) throws IOException {
  StringWriter writer=new StringWriter();
  try {
    write(reader,writer);
    return writer.getBuffer().toString();
  }
  finally {
    writer.close();
  }
}
