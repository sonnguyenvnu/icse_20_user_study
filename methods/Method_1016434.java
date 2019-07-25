/** 
 * open text files for reading. If the files are compressed, choose the appropriate decompression method automatically
 * @param f
 * @return the input stream for the file
 * @throws IOException
 */
public static InputStream read(File f) throws IOException {
  InputStream is=new BufferedInputStream(new FileInputStream(f));
  if (f.toString().endsWith(".bz2"))   is=new BZip2CompressorInputStream(is);
  if (f.toString().endsWith(".gz"))   is=new GZIPInputStream(is);
  return is;
}
