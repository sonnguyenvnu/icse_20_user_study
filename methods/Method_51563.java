/** 
 * Applies all configured filters against the given input stream. The resulting reader will contain the original ruleset modified by the filters.
 * @param stream the original ruleset file input stream
 * @return a reader, from which the filtered ruleset can be read
 * @throws IOException if the stream couldn't be read
 */
public Reader filterRuleSetFile(InputStream stream) throws IOException {
  byte[] bytes=IOUtils.toByteArray(stream);
  String encoding=determineEncoding(bytes);
  String ruleset=new String(bytes,encoding);
  ruleset=applyAllFilters(ruleset);
  return new StringReader(ruleset);
}
