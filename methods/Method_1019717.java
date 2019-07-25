@Override public void visit(TextListStyleElement ele){
  currentListPropertiesMap=new HashMap<Integer,StyleListProperties>();
  computeStyle(ele,false);
  currentStyle.setListPropertiesMap(currentListPropertiesMap);
  currentListPropertiesMap=null;
}
