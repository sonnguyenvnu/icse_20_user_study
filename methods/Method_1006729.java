/** 
 * Load the content of the zip entry, and return it as a byte array.
 * @return the entry as a byte[] array
 * @throws IOException If an I/O exception occurs.
 * @throws InterruptedException If the thread was interrupted.
 */
public byte[] load() throws IOException, InterruptedException {
  try (InputStream is=open()){
    return FileUtils.readAllBytesAsArray(is,uncompressedSize);
  }
 }
