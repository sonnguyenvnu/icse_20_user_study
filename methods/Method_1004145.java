/** 
 * Creates a 'line' element.
 * @param nr line number
 * @param line line object to write out
 * @throws IOException in case of problems with the underlying output
 */
public void line(final int nr,final ILine line) throws IOException {
  final ReportElement element=element("line");
  element.attr("nr",nr);
  counterAttributes(element,"mi","ci",line.getInstructionCounter());
  counterAttributes(element,"mb","cb",line.getBranchCounter());
}
