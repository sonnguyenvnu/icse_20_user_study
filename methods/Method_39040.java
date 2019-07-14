/** 
 * Resolves action method for given action class ane method name.
 */
public Method resolveActionMethod(final Class<?> actionClass,final String methodName){
  MethodDescriptor methodDescriptor=ClassIntrospector.get().lookup(actionClass).getMethodDescriptor(methodName,false);
  if (methodDescriptor == null) {
    throw new MadvocException("Public method not found: " + actionClass.getSimpleName() + "#" + methodName);
  }
  return methodDescriptor.getMethod();
}
