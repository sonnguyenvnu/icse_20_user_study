@Override public void write(Element element,Project project){
  Element scopeTypeXML=new Element(SCOPE_TYPE);
  scopeTypeXML.setAttribute(SCOPE_TYPE,myScopeType.name());
  scopeTypeXML.setAttribute(MODULE,myModule == null ? "" : myModule);
  scopeTypeXML.setAttribute(MODEL,myModel == null ? "" : myModel);
  element.addContent(scopeTypeXML);
}
