@Override public void visit(OdfElement ele){
  Node node=ele.getFirstChild();
  int nodeType=-1;
  while (node != null) {
    nodeType=node.getNodeType();
switch (nodeType) {
case Node.ELEMENT_NODE:
      OdfElement element=(OdfElement)node;
    element.accept(this);
  break;
case Node.TEXT_NODE:
processTextNode((Text)node);
}
node=node.getNextSibling();
}
}
