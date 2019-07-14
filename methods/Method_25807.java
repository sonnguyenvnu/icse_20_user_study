@SuppressWarnings("unchecked") private static Type getTypeFromTypeMapping(Type m,String namedTypeArg){
  try {
    Field substField=m.getClass().getDeclaredField("this$0");
    substField.setAccessible(true);
    Object subst=substField.get(m);
    Field fromField=subst.getClass().getDeclaredField("from");
    Field toField=subst.getClass().getDeclaredField("to");
    fromField.setAccessible(true);
    toField.setAccessible(true);
    List<Type> types=(List<Type>)fromField.get(subst);
    List<Type> calledTypes=(List<Type>)toField.get(subst);
    for (int i=0; i < types.size(); i++) {
      Type type=types.get(i);
      if (type.toString().equals(namedTypeArg)) {
        return calledTypes.get(i);
      }
    }
  }
 catch (  ReflectiveOperationException ignored) {
  }
  return null;
}
