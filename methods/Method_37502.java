/** 
 * Writes data to  {@link File} destination.
 * @param dest   destination {@link File}
 * @param data   Data as a {@link String}
 * @param off    the start offset in the data.
 * @param len    the number of bytes to write.
 * @param append {@code true} if appending; {@code false} if {@link File} should be overwritten.
 * @throws IOException if something went wrong.
 */
protected static void outBytes(final File dest,final byte[] data,final int off,final int len,final boolean append) throws IOException {
  if (dest.exists()) {
    checkIsFile(dest);
  }
  FileOutputStream out=null;
  try {
    out=new FileOutputStream(dest,append);
    out.write(data,off,len);
  }
  finally {
    StreamUtil.close(out);
  }
}
