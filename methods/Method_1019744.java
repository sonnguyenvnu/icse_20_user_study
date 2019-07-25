@Override public void visit(TextOutlineStyleElement ele){
  currentListPropertiesMap=new HashMap<Integer,StyleListProperties>();
  computeStyle(ele,true);
  currentStyle.setOutlinePropertiesMap(currentListPropertiesMap);
  currentListPropertiesMap=null;
}
