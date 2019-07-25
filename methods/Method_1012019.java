@Override public void write(Element element,Project project) throws CantSaveSomethingException {
  element.setAttribute(CAPTION,myCaption);
  if (myAdditionalInfo != null) {
    element.setAttribute(INFO,myAdditionalInfo);
  }
  element.setAttribute(EXCLUDED,Boolean.toString(myIsExcluded));
  element.setAttribute(ISRESULT,Boolean.toString(myIsPathTail));
  element.setAttribute(RESULTS_SECTION,Boolean.toString(myResultsSection));
  Element roleXML=new Element(ROLE);
  PathItemRole.write(myRole,roleXML);
  element.addContent(roleXML);
  for (BaseNodeData ch=myHead; ch != null; ch=ch.myNext) {
    Element childElement=new Element("child");
    Element ce=new Element("instance");
    ce.setAttribute("qcn",ch.getClass().getName());
    childElement.addContent(ce);
    ch.write(childElement,project);
    element.addContent(childElement);
  }
}
