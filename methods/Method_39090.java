/** 
 * Replaces all  {@link BaseActionWrapperStack} with stack values.
 */
protected Class<? extends T>[] expand(final Class<? extends T>[] actionWrappers){
  if (actionWrappers == null) {
    return null;
  }
  List<Class<? extends T>> list=new ArrayList<>(actionWrappers.length);
  list.addAll(Arrays.asList(actionWrappers));
  int i=0;
  while (i < list.size()) {
    Class<? extends T> wrapperClass=list.get(i);
    if (wrapperClass == null) {
      continue;
    }
    if (ClassUtil.isTypeOf(wrapperClass,BaseActionWrapperStack.class)) {
      BaseActionWrapperStack stack=(BaseActionWrapperStack)resolve(wrapperClass);
      list.remove(i);
      Class<? extends T>[] stackWrappers=stack.getWrappers();
      if (stackWrappers != null) {
        list.addAll(i,Arrays.asList(stackWrappers));
      }
      i--;
    }
    i++;
  }
  return list.toArray(new Class[0]);
}
