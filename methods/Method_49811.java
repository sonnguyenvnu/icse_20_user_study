@Override public SMILElement getDocumentElement(){
  Node rootElement=getFirstChild();
  if (rootElement == null || !(rootElement instanceof SMILElement)) {
    rootElement=createElement("smil");
    appendChild(rootElement);
  }
  return (SMILElement)rootElement;
}
