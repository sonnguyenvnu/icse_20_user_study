/** 
 * Copies data from  {@link DataSource} to a new {@link FastCharArrayWriter} and returns this.
 * @param input {@link DataSource} to copy from.
 * @return new {@link FastCharArrayWriter} with data from input.
 * @see #copy(InputStream)
 */
public static FastCharArrayWriter copy(final DataSource input) throws IOException {
  return copy(input.getInputStream());
}
