/** 
 * ????????IO??????
 * @param path
 * @return
 * @throws IOException
 */
public static OutputStream newOutputStream(String path) throws IOException {
  if (IOAdapter == null)   return new FileOutputStream(path);
  return IOAdapter.create(path);
}
