/** 
 * Main entry for visit XWPFDocument.
 * @param out
 * @throws Exception
 */
public void start() throws Exception {
  T container=startVisitDocument();
  visitBodyElements(document,container);
  endVisitDocument();
}
