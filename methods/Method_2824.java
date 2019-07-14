/** 
 * ????????IO??????
 * @param path
 * @return
 * @throws IOException
 */
public static InputStream newInputStream(String path) throws IOException {
  if (IOAdapter == null)   return new FileInputStream(path);
  return IOAdapter.open(path);
}
