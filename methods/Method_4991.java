/** 
 * Deserializes one action that was serialized with  {@link #serializeToStream(OutputStream)} fromthe  {@code input}. <p>The caller is responsible for closing the given  {@link InputStream}.
 * @param input The stream from which to read.
 * @return The deserialized action.
 * @throws IOException If there is an IO error reading from {@code input}, or if the data could not be deserialized.
 */
public static DownloadAction deserializeFromStream(InputStream input) throws IOException {
  return readFromStream(new DataInputStream(input));
}
