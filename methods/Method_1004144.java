/** 
 * Creates a 'sourcefile' element.
 * @param name value for the name attribute
 * @return 'sourcefile' element
 * @throws IOException in case of problems with the underlying output
 */
public ReportElement sourcefile(final String name) throws IOException {
  return namedElement("sourcefile",name);
}
