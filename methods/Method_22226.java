/** 
 * Reads an InputStream into a string respecting blocking settings.
 * @param input  the stream
 * @param filter should return false for lines which should be excluded
 * @param limit  the maximum number of lines to read (the last x lines are kept)
 * @return the String that was read.
 * @throws IOException if the stream cannot be read.
 */
@NonNull private String streamToString(@NonNull CoreConfiguration config,@NonNull InputStream input,@Nullable Predicate<String> filter,int limit) throws IOException {
  final StreamReader reader=new StreamReader(input).setFilter(filter).setLimit(limit);
  if (config.logcatReadNonBlocking()) {
    reader.setTimeout(READ_TIMEOUT);
  }
  return reader.read();
}
