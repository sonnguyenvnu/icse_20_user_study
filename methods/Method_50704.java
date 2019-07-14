protected void checkMatches(PropertyDescriptor<Pattern> propertyDescriptor,Pattern overridePattern,ApexNode<?> node,Object data){
  String name=node.getImage();
  if (!overridePattern.matcher(name).matches()) {
    String displayName=displayName(propertyDescriptor.name());
    addViolation(data,node,new Object[]{displayName,name,overridePattern.toString()});
  }
}
