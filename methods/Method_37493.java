/** 
 * Writes characters to  {@link File} destination.
 * @param dest     destination {@link File}
 * @param data     Data as a {@link String}
 * @param encoding Encoding as a {@link String}
 * @param append   {@code true} if appending; {@code false} if {@link File} should be overwritten.
 * @throws IOException if something went wrong.
 */
protected static void outChars(final File dest,final char[] data,final String encoding,final boolean append) throws IOException {
  if (dest.exists()) {
    checkIsFile(dest);
  }
  Writer out=new BufferedWriter(StreamUtil.outputStreamWriterOf(new FileOutputStream(dest,append),encoding));
  try {
    out.write(data);
  }
  finally {
    StreamUtil.close(out);
  }
}
