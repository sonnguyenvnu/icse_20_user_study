private StringBuilder path(StringBuilder builder,Character separator){
  DataElement element=this;
  while (element.getParent() != null) {
    builder.insert(0,separator);
    builder.insert(1,element.getName());
    element=element.getParent();
  }
  return builder;
}
