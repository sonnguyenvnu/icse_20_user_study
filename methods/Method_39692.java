/** 
 * Static factory of next target. It handles special cases of maps, sets and lists. In case target can not be proxified (like for Java classes) it returns <code>null</code>.
 */
@SuppressWarnings("unchecked") public <T>T continueWith(final Object currentInstance,final String methodName,final Class<T> target){
  final Class currentClass=currentInstance.getClass();
  final Method method;
  try {
    method=currentClass.getDeclaredMethod(methodName);
  }
 catch (  final NoSuchMethodException e) {
    throw new PathrefException("Not a getter: " + methodName,e);
  }
  if (!ClassUtil.isBeanPropertyGetter(method)) {
    throw new PathrefException("Not a getter: " + methodName);
  }
  final String getterName=ClassUtil.getBeanPropertyGetterName(method);
  append(getterName);
  if (ClassUtil.isTypeOf(target,List.class)) {
    final Class componentType=ClassUtil.getComponentType(method.getGenericReturnType(),currentClass,0);
    if (componentType == null) {
      throw new PathrefException("Unknown component name for: " + methodName);
    }
    return (T)new ArrayList(){
      @Override public Object get(      final int index){
        if (index >= 0) {
          append("[" + index + "]");
        }
        return new Pathref<>(componentType,Pathref.this).get();
      }
    }
;
  }
  try {
    return new Pathref<>(target,this).get();
  }
 catch (  final Exception ex) {
    return null;
  }
}
