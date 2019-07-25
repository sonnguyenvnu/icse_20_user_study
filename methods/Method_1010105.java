@Override public void write(Element element,Project project) throws CantSaveSomethingException {
  super.write(element,project);
  Element finderXML;
  if (myFinder instanceof ReloadableFinder) {
    finderXML=new Element(GENERATED_FINDER);
    String finderIdentity=((ReloadableFinder)myFinder).getPersistenceIdentity();
    finderXML.setAttribute(CLASS_NAME,finderIdentity);
  }
 else {
    finderXML=new Element(FINDER);
    finderXML.setAttribute(CLASS_NAME,myFinder.getClass().getName());
  }
  element.addContent(finderXML);
}
