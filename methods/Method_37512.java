/** 
 * Returns either new  {@link FileInputStream} or new {@link UnicodeInputStream}.
 * @return either {@link FileInputStream} or {@link UnicodeInputStream}.
 * @throws IOException if something went wrong.
 * @see #unicodeInputStreamOf(InputStream,String)
 */
private static InputStream streamOf(final File file,final String encoding) throws IOException {
  InputStream in=new FileInputStream(file);
  if (encoding.startsWith("UTF")) {
    in=unicodeInputStreamOf(in,encoding);
  }
  return in;
}
