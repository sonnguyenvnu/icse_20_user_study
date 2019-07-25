@Override public void read(Element element,Project project) throws CantLoadSomethingException {
  Element nodeXML=element.getChild(NODE);
  if (nodeXML == null || nodeXML.getAttribute(REF) == null) {
    throw new CantLoadSomethingException("node is null");
  }
  myNodePointer=PersistenceFacade.getInstance().createNodeReference(nodeXML.getAttributeValue(REF));
  myTitle=nodeXML.getAttributeValue(CAPTION);
}
