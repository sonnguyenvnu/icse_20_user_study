/** 
 * Copies data from  {@link DataSource} to a new {@link FastByteArrayOutputStream} and returns this.
 * @param input {@link DataSource} to copy from.
 * @return new {@link FastByteArrayOutputStream} with data from input.
 * @see #copyToOutputStream(InputStream)
 */
public static FastByteArrayOutputStream copyToOutputStream(final DataSource input) throws IOException {
  return copyToOutputStream(input.getInputStream());
}
