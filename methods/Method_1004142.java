/** 
 * Creates a 'group' element.
 * @param name value for the name attribute
 * @return 'group' element
 * @throws IOException in case of problems with the underlying output
 */
public ReportElement group(final String name) throws IOException {
  return namedElement("group",name);
}
