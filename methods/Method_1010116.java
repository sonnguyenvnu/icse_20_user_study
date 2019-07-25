@Override public void write(Element element,Project project) throws CantSaveSomethingException {
  Element nodeXML=new Element(NODE);
  nodeXML.setAttribute(REF,PersistenceFacade.getInstance().asString(myNodePointer));
  if (myTitle != null) {
    nodeXML.setAttribute(CAPTION,myTitle);
  }
  element.addContent(nodeXML);
}
