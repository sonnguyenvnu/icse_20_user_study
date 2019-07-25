@Override public void write(Element element,Project project){
  Element findersXML=new Element(FINDERS);
  for (  String finderClassName : myFindersClassNames) {
    Element finderXML=new Element(FINDER);
    finderXML.setAttribute(CLASS_NAME,finderClassName);
    findersXML.addContent(finderXML);
  }
  element.addContent(findersXML);
}
