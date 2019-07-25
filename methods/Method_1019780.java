/** 
 * Main entry for visit XWPFDocument.
 * @throws Exception
 */
public void start() throws Exception {
  T container=startVisitDocument();
  List<IBodyElement> bodyElements=document.getBodyElements();
  visitBodyElements(bodyElements,container);
  endVisitDocument();
}
