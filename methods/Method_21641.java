private String getChildrenAggName(Field field){
  String prefix;
  if (field instanceof MethodField) {
    String childType=field.getChildType();
    if (childType != null) {
      prefix=childType;
    }
 else {
      prefix=field.getAlias();
    }
  }
 else {
    prefix=field.getName();
  }
  return prefix + "@CHILDREN";
}
