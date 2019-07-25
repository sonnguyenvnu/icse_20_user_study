@Override public void write(Element element,Project project) throws CantSaveSomethingException {
  super.write(element,project);
  Element modelXML=new Element(MODEL);
  modelXML.setAttribute(UID,PersistenceFacade.getInstance().asString(myModelReference));
  element.addContent(modelXML);
}
