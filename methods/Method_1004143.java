/** 
 * Creates a 'method' element.
 * @param coverage method coverage node to write out
 * @return 'method' element
 * @throws IOException in case of problems with the underlying output
 */
public ReportElement method(final IMethodCoverage coverage) throws IOException {
  final ReportElement element=namedElement("method",coverage.getName());
  element.attr("desc",coverage.getDesc());
  final int line=coverage.getFirstLine();
  if (line != -1) {
    element.attr("line",line);
  }
  return element;
}
