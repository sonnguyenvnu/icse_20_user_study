protected void _element(final Element element) throws IOException {
  String nodeName=resolveNodeName(element);
  appendable.append('<');
  appendable.append(nodeName);
  int attrCount=element.getAttributesCount();
  if (attrCount != 0) {
    for (int i=0; i < attrCount; i++) {
      Attribute attr=element.getAttribute(i);
      appendable.append(' ');
      renderAttribute(element,attr,appendable);
    }
  }
  int childCount=element.getChildNodesCount();
  if (element.selfClosed && childCount == 0) {
    appendable.append("/>");
    return;
  }
  appendable.append('>');
  if (element.voidElement) {
    return;
  }
  if (childCount != 0) {
    elementBody(element);
  }
  appendable.append("</");
  appendable.append(nodeName);
  appendable.append('>');
}
