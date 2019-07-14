private String resolveBeanName(String propertyName){
  int index=propertyName.indexOf(DOT);
  return index > -1 ? propertyName.substring(0,index) : null;
}
