/** 
 * Returns files content from disk file. If error occurs, it returns <code>null</code>
 */
@Override public byte[] getFileContent() throws IOException {
  return FileUtil.readBytes(file);
}
