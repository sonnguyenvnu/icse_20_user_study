public void write(Element element,Project project) throws CantSaveSomethingException {
  Element viewOptionsXML=new Element(VIEW_OPTIONS);
  ViewOptions op=getComponentsViewOptions();
  op.write(viewOptionsXML,project);
  element.addContent(viewOptionsXML);
  Element contentsXML=new Element(CONTENTS);
  myContents.write(contentsXML,project);
  element.addContent(contentsXML);
}
