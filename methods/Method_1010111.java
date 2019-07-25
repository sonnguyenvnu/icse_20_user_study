@Override public void read(Element element,Project project) throws CantLoadSomethingException {
  Element modelXML=element.getChild(MODEL);
  try {
    myModelReference=PersistenceFacade.getInstance().createModelReference(modelXML.getAttributeValue(UID));
  }
 catch (  IllegalArgumentException ex) {
    throw new CantLoadSomethingException("cannot parse model reference",ex);
  }
}
