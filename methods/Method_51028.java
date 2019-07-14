protected void appendElement(final org.w3c.dom.Node parentNode){
  final DocumentNavigator docNav=new DocumentNavigator();
  Document ownerDocument=parentNode.getOwnerDocument();
  if (ownerDocument == null) {
    ownerDocument=(Document)parentNode;
  }
  final String elementName=docNav.getElementName(this);
  final Element element=ownerDocument.createElement(elementName);
  parentNode.appendChild(element);
  for (final Iterator<Attribute> iter=docNav.getAttributeAxisIterator(this); iter.hasNext(); ) {
    final Attribute attr=iter.next();
    element.setAttribute(attr.getName(),attr.getStringValue());
  }
  for (final Iterator<Node> iter=docNav.getChildAxisIterator(this); iter.hasNext(); ) {
    final AbstractNode child=(AbstractNode)iter.next();
    child.appendElement(element);
  }
}
