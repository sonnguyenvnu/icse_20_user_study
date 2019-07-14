@Override public String name(){
  String typeName=type.name();
  int index=typeName.lastIndexOf(RELATION_INDEX_SEPARATOR);
  Preconditions.checkArgument(index > 0 && index < typeName.length() - 1,"Invalid name encountered: %s",typeName);
  return typeName.substring(index + 1,typeName.length());
}
