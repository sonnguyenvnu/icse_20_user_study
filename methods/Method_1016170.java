/** 
 * Returns whether document with the specified ID is in this pane or not.
 * @param documentId ID of the document to look for
 * @return true if document with the specified ID is in this pane, false otherwise
 */
public boolean contains(final String documentId){
  for (  final T document : data) {
    if (document.getId().equals(documentId)) {
      return true;
    }
  }
  return false;
}
