@Override protected Object getValue(Context ctx,Element base) throws IOException {
  Element el=(Element)DOMHelper.getElementNode(base,path);
  if (el == null) {
    return null;
  }
  el.normalize();
  Node node=el.getFirstChild();
  if (node == null) {
    return "";
  }
  Range range=((DocumentRange)el.getOwnerDocument()).createRange();
  range.setStartBefore(node);
  range.setEndAfter(el.getLastChild());
  DocumentFragment fragment=range.cloneContents();
  boolean asDOM=setter.getType() == DocumentFragment.class;
  return asDOM ? fragment : DOMSerializer.toString(fragment,DEFAULT_FORMAT);
}
