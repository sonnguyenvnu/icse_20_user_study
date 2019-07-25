/** 
 * Update the last modified time.
 * @param openReadOnly if the file was opened in read-only mode
 */
void touch(boolean openReadOnly) throws IOException {
  if (isReadOnly || openReadOnly) {
    throw new IOException("Read only");
  }
  lastModified=System.currentTimeMillis();
}
