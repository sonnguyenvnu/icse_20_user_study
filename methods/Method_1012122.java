@Override public void read(Element element,Project project){
  Element findersXML=element.getChild(FINDERS);
  for (  Element finderXML : findersXML.getChildren(FINDER)) {
    String finderName=finderXML.getAttribute(CLASS_NAME).getValue();
    myFindersClassNames.add(finderName);
  }
}
