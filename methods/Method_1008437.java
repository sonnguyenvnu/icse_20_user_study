/** 
 * Create a new  {@link XContentBuilder} using the given {@link XContent} content and some inclusive and/or exclusive filters.<p> The builder uses an internal  {@link BytesStreamOutput} output stream to build the content. When both exclusive andinclusive filters are provided, the underlying builder will first use exclusion filters to remove fields and then will check the remaining fields against the inclusive filters. <p>
 * @param xContent the {@link XContent}
 * @param includes the inclusive filters: only fields and objects that match the inclusive filters will be written to the output.
 * @param excludes the exclusive filters: only fields and objects that don't match the exclusive filters will be written to the output.
 * @throws IOException if an {@link IOException} occurs while building the content
 */
public static XContentBuilder builder(XContent xContent,Set<String> includes,Set<String> excludes) throws IOException {
  return new XContentBuilder(xContent,new BytesStreamOutput(),includes,excludes);
}
