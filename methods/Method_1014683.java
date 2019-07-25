/** 
 * Cache for a stream
 * @param key the file name.
 * @return OutputStream stream for writing data.
 * @throws FileNotFoundException if the file can not be created.
 */
public OutputStream put(String key) throws FileNotFoundException {
  return new xFileOutputStream(mCache.newFile(key));
}
