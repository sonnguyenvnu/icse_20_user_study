@Override public Q types(String... types){
  if (types == null)   types=NO_TYPES;
  for (  String type : types)   Preconditions.checkArgument(StringUtils.isNotBlank(type),"Invalid type: %s",type);
  this.types=types;
  return getThis();
}
