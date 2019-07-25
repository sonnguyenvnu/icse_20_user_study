@Override public void read(Element element,Project project) throws CantLoadSomethingException {
  super.read(element,project);
  try {
    myNodePointer=PersistenceFacade.getInstance().createNodeReference(element.getAttributeValue(NODE));
    myIsRootNode=Boolean.parseBoolean(element.getAttributeValue(IS_ROOT));
  }
 catch (  Exception ex) {
    throw new CantLoadSomethingException(ex);
  }
}
