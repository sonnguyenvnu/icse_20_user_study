/** 
 * Writes data using encoding to  {@link File}.
 * @param dest     destination {@link File}
 * @param data     Data as a {@link String}
 * @param encoding Encoding as a {@link String}
 * @param append   {@code true} if appending; {@code false} if {@link File} should be overwritten.
 * @throws IOException if something went wrong.
 */
protected static void outString(final File dest,final String data,final String encoding,final boolean append) throws IOException {
  if (dest.exists()) {
    checkIsFile(dest);
  }
  FileOutputStream out=null;
  try {
    out=new FileOutputStream(dest,append);
    out.write(data.getBytes(encoding));
  }
  finally {
    StreamUtil.close(out);
  }
}
