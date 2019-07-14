@Override @SuppressWarnings("unchecked") public List<Node> findChildNodesWithXPath(final String xpathString) throws JaxenException {
  return new BaseXPath(xpathString,new DocumentNavigator()).selectNodes(this);
}
