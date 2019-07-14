private static Type getMapping(Type type){
  if (type == null) {
    return null;
  }
  try {
    Field valField=type.getClass().getDeclaredField("val$t");
    valField.setAccessible(true);
    return (Type)valField.get(type);
  }
 catch (  ReflectiveOperationException e) {
    return type;
  }
}
