/** 
 * Builds action runtime configuration on founded action class. Action classes are annotated with  {@link jodd.madvoc.meta.MadvocAction} annotation.
 */
@SuppressWarnings("NonConstantStringShouldBeStringBuffer") protected void acceptActionClass(final Class<?> actionClass){
  if (actionClass == null) {
    return;
  }
  if (!checkClass(actionClass)) {
    return;
  }
  if (actionClass.getAnnotation(MadvocAction.class) == null) {
    return;
  }
  ClassDescriptor cd=ClassIntrospector.get().lookup(actionClass);
  MethodDescriptor[] allMethodDescriptors=cd.getAllMethodDescriptors();
  for (  MethodDescriptor methodDescriptor : allMethodDescriptors) {
    if (!methodDescriptor.isPublic()) {
      continue;
    }
    final Method method=methodDescriptor.getMethod();
    final boolean hasAnnotation=actionConfigManager.hasActionAnnotationOn(method);
    if (!hasAnnotation) {
      continue;
    }
    webappConfigurations.add(() -> actionsManager.registerAction(actionClass,method,null));
  }
}
