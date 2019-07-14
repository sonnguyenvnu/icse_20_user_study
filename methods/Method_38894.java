/** 
 * Initializes child nodes list when needed. Also fix owner document for new node, if needed.
 */
protected void initChildNodes(final Node newNode){
  if (childNodes == null) {
    childNodes=new ArrayList<>();
  }
  if (ownerDocument != null) {
    if (newNode.ownerDocument != ownerDocument) {
      changeOwnerDocument(newNode,ownerDocument);
    }
  }
}
