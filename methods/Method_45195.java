private void trimChild(final Node node,final Node child){
  if (child instanceof Text) {
    if (isNullOrEmpty(child.getNodeValue().trim())) {
      node.removeChild(child);
    }
    return;
  }
  if (child instanceof Element) {
    trimNode(child);
  }
}
