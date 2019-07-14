/** 
 * Creates new object or a <code>HashMap</code> if type is not specified.
 */
protected Object newObjectInstance(final Class targetType){
  if (targetType == null || targetType == Map.class) {
    return mapSupplier.get();
  }
  final ClassDescriptor cd=ClassIntrospector.get().lookup(targetType);
  final CtorDescriptor ctorDescriptor=cd.getDefaultCtorDescriptor(true);
  if (ctorDescriptor == null) {
    throw new JsonException("Default ctor not found for: " + targetType.getName());
  }
  try {
    return ctorDescriptor.getConstructor().newInstance();
  }
 catch (  Exception e) {
    throw new JsonException(e);
  }
}
