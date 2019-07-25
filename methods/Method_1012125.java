@Override public void read(Element element,Project project){
  Element scopeTypeXML=element.getChild(SCOPE_TYPE);
  myScopeType=ScopeType.valueOf(scopeTypeXML.getAttributeValue(SCOPE_TYPE));
  myModule=scopeTypeXML.getAttributeValue(MODULE);
  myModel=scopeTypeXML.getAttributeValue(MODEL);
}
