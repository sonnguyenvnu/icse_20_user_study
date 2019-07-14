@SuppressWarnings("unchecked") private static void getSubst(Type m,List<Type> from,List<Type> to){
  try {
    Field substField=m.getClass().getDeclaredField("this$0");
    substField.setAccessible(true);
    Object subst=substField.get(m);
    Field fromField=subst.getClass().getDeclaredField("from");
    Field toField=subst.getClass().getDeclaredField("to");
    fromField.setAccessible(true);
    toField.setAccessible(true);
    from.addAll((Collection<Type>)fromField.get(subst));
    to.addAll((Collection<Type>)toField.get(subst));
  }
 catch (  ReflectiveOperationException e) {
    return;
  }
}
