/** 
 * Performs the fix for elements.
 */
protected void fixElements(){
  for (  Element fosterElement : fosterElements) {
    Element lastTable=findLastTable(fosterElement);
    Node fosterElementParent=fosterElement.getParentNode();
    Node[] fosterChilds=fosterElement.getChildNodes();
    for (    Node fosterChild : fosterChilds) {
      if (fosterChild.getNodeType() == Node.NodeType.ELEMENT) {
        if (isOneOfTableElements((Element)fosterChild)) {
          fosterChild.detachFromParent();
          fosterElementParent.insertBefore(fosterChild,fosterElement);
        }
      }
    }
    fosterElement.detachFromParent();
    lastTable.getParentNode().insertBefore(fosterElement,lastTable);
  }
}
