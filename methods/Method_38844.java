/** 
 * Finds foster elements. Returns <code>true</code> if there was no change in DOM tree of the parent element. Otherwise, returns <code>false</code> meaning that parent will scan its childs again.
 */
protected boolean findFosterNodes(final Node node){
  boolean isTable=false;
  if (!lastTables.isEmpty()) {
    if (node.getNodeType() == Node.NodeType.TEXT) {
      String value=node.getNodeValue();
      if (!StringUtil.isBlank(value)) {
        if (isParentNodeOneOfFosterTableElements(node.getParentNode())) {
          fosterTexts.add((Text)node);
        }
      }
    }
  }
  if (node.getNodeType() == Node.NodeType.ELEMENT) {
    Element element=(Element)node;
    isTable=isTableElement(node);
    if (isTable) {
      lastTables.add(element);
    }
 else {
      if (!lastTables.isEmpty()) {
        Node parentNode=node.getParentNode();
        if (isParentNodeOneOfFosterTableElements(parentNode) && !isOneOfTableElements(element)) {
          String elementNodeName=element.getNodeName().toLowerCase();
          if (elementNodeName.equals("form")) {
            if (element.getChildNodesCount() > 0) {
              Node[] formChildNodes=element.getChildNodes();
              parentNode.insertAfter(formChildNodes,element);
              return false;
            }
 else {
              return true;
            }
          }
          if (elementNodeName.equals("input")) {
            String inputType=element.getAttribute("type");
            if (inputType.equals("hidden")) {
              return true;
            }
          }
          fosterElements.add(element);
        }
      }
 else {
      }
    }
  }
  allchilds:   while (true) {
    int childs=node.getChildNodesCount();
    for (int i=0; i < childs; i++) {
      Node childNode=node.getChild(i);
      boolean done=findFosterNodes(childNode);
      if (!done) {
        continue allchilds;
      }
    }
    break;
  }
  if (isTable) {
    int size=lastTables.size();
    if (size > 0) {
      lastTables.remove(size - 1);
    }
  }
  return true;
}
