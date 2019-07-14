/** 
 * ????BufferedWriter
 * @param path
 * @return
 * @throws FileNotFoundException
 * @throws UnsupportedEncodingException
 */
public static BufferedWriter newBufferedWriter(String path) throws IOException {
  return new BufferedWriter(new OutputStreamWriter(IOUtil.newOutputStream(path),"UTF-8"));
}
