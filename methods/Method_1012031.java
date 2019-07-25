@Override public void read(Element element,Project project){
  Element viewOptionsXML=element.getChild(OPTIONS);
  if (viewOptionsXML == null) {
    return;
  }
  myCategory=Boolean.valueOf(viewOptionsXML.getAttributeValue(CATEGORY_OPTION));
  myModule=Boolean.valueOf(viewOptionsXML.getAttributeValue(MODULE_OPTION));
  myModel=Boolean.valueOf(viewOptionsXML.getAttributeValue(MODEL_OPTION));
  myRoot=Boolean.valueOf(viewOptionsXML.getAttributeValue(ROOT_OPTION));
  myNamedPath=Boolean.valueOf(viewOptionsXML.getAttributeValue(PATH_OPTION));
  myCount=Boolean.valueOf(viewOptionsXML.getAttributeValue(COUNT_OPTION));
  myInfo=Boolean.valueOf(viewOptionsXML.getAttributeValue(INFO_OPTION));
  myShowSearchedNodes=Boolean.valueOf(viewOptionsXML.getAttributeValue(SHOW_SEARCHED_NODES_OPTION));
  myGroupSearchedNodes=Boolean.valueOf(viewOptionsXML.getAttributeValue(GROUP_SEARCHED_NODES_OPTION));
  myAutoscrolls=Boolean.valueOf(viewOptionsXML.getAttributeValue(AUTOSCROLL_OPTION));
}
