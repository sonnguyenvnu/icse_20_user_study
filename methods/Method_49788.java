public Node setNamedItem(Node arg) throws DOMException {
  Node existing=getNamedItem(arg.getNodeName());
  if (existing != null) {
    mNodes.remove(existing);
  }
  mNodes.add(arg);
  return existing;
}
