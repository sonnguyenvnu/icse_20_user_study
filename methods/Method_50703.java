protected void checkMatches(PropertyDescriptor<Pattern> propertyDescriptor,ApexNode<?> node,Object data){
  Pattern regex=getProperty(propertyDescriptor);
  String name=node.getImage();
  if (!regex.matcher(name).matches()) {
    String displayName=displayName(propertyDescriptor.name());
    addViolation(data,node,new Object[]{displayName,name,regex.toString()});
  }
}
