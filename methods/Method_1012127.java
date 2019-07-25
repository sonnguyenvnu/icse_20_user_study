@Override public void read(Element element,Project project){
  Element flagsXML=element.getChild(FLAGS);
  myShowOneResult=Boolean.parseBoolean(flagsXML.getAttribute(SHOW_ONE_RESULT).getValue());
  myNewTab=Boolean.parseBoolean(flagsXML.getAttribute(NEW_TAB).getValue());
}
