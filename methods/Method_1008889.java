/** 
 * @param documentPart
 * @param remediate
 * @return
 * @throws Exception
 */
public BookmarksStatus check(MainDocumentPart documentPart,boolean remediate) throws Exception {
  return check(documentPart.getContent(),remediate);
}
