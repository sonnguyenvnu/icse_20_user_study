public Node appendChild(Node newChild) throws DOMException {
  ((NodeImpl)newChild).setParentNode(this);
  mChildNodes.remove(newChild);
  mChildNodes.add(newChild);
  return newChild;
}
