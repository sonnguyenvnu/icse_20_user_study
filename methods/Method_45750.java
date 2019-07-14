/** 
 * ??????
 * @param file ??
 * @return ????
 * @throws IOException ??IO??
 */
public static String file2String(File file) throws IOException {
  if (file == null || !file.exists() || !file.isFile() || !file.canRead()) {
    return null;
  }
  FileReader reader=null;
  StringWriter writer=null;
  try {
    reader=new FileReader(file);
    writer=new StringWriter();
    char[] cbuf=new char[1024];
    int len=0;
    while ((len=reader.read(cbuf)) != -1) {
      writer.write(cbuf,0,len);
    }
    return writer.toString();
  }
  finally {
    IOUtils.closeQuietly(reader);
    IOUtils.closeQuietly(writer);
  }
}
