/** 
 * Creates action method arguments.
 */
@SuppressWarnings({"unchecked","NullArgumentToVariableArgMethod"}) protected Object createActionMethodArgument(final Class type,final Object action){
  try {
    if (type.getEnclosingClass() == null || Modifier.isStatic(type.getModifiers())) {
      return ClassUtil.newInstance(type);
    }
 else {
      final Constructor ctor=type.getDeclaredConstructor(type.getDeclaringClass());
      ctor.setAccessible(true);
      return ctor.newInstance(action);
    }
  }
 catch (  final Exception ex) {
    throw new MadvocException(ex);
  }
}
