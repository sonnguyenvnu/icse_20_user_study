@Override public void write(Element element,Project project){
  Element viewOptionsXML=new Element(OPTIONS);
  viewOptionsXML.setAttribute(CATEGORY_OPTION,Boolean.toString(myCategory));
  viewOptionsXML.setAttribute(MODULE_OPTION,Boolean.toString(myModule));
  viewOptionsXML.setAttribute(MODEL_OPTION,Boolean.toString(myModel));
  viewOptionsXML.setAttribute(ROOT_OPTION,Boolean.toString(myRoot));
  viewOptionsXML.setAttribute(PATH_OPTION,Boolean.toString(myNamedPath));
  viewOptionsXML.setAttribute(COUNT_OPTION,Boolean.toString(myCount));
  viewOptionsXML.setAttribute(INFO_OPTION,Boolean.toString(myInfo));
  viewOptionsXML.setAttribute(SHOW_SEARCHED_NODES_OPTION,Boolean.toString(myShowSearchedNodes));
  viewOptionsXML.setAttribute(GROUP_SEARCHED_NODES_OPTION,Boolean.toString(myGroupSearchedNodes));
  viewOptionsXML.setAttribute(AUTOSCROLL_OPTION,Boolean.toString(myAutoscrolls));
  element.addContent(viewOptionsXML);
}
