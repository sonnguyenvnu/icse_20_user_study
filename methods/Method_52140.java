void checkMatches(T node,PropertyDescriptor<Pattern> regex,Object data){
  String name=nameExtractor(node);
  if (!getProperty(regex).matcher(name).matches()) {
    addViolation(data,node,new Object[]{kindDisplayName(node,regex),name,getProperty(regex).toString()});
  }
}
