@Override public void write(Element element,Project project) throws CantSaveSomethingException {
  for (  BaseNode child : myChildren) {
    Element childXML=new Element(CHILD);
    childXML.setAttribute(CHILD_CLASS,child.getClass().getName());
    child.write(childXML,project);
    element.addContent(childXML);
  }
}
