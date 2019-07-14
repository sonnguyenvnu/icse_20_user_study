/** 
 * Returns cached file bytes. If file is not cached it will be read and put in the cache (if all the rules are satisfied).
 */
public byte[] getFileBytes(final File file) throws IOException {
  byte[] bytes=cache.get(file);
  if (bytes != null) {
    return bytes;
  }
  bytes=FileUtil.readBytes(file);
  if ((maxFileSize != 0) && (file.length() > maxFileSize)) {
    return bytes;
  }
  usedSize+=bytes.length;
  cache.put(file,bytes);
  return bytes;
}
