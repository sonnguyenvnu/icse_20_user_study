@Override public void write(Element element,Project project) throws CantSaveSomethingException {
  Element modelXML=new Element(MODEL);
  modelXML.setAttribute(UID,myModelReference.toString());
  element.addContent(modelXML);
}
