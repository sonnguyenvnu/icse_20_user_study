/** 
 * Returns the content of file upload item.
 */
@Override public byte[] getFileContent() throws IOException {
  if (data != null) {
    return data;
  }
  if (tempFile != null) {
    return FileUtil.readBytes(tempFile);
  }
  return null;
}
