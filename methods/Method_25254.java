Nullness fieldNullness(@Nullable ClassAndField accessed,@Nullable AccessPath path,AccessPathValues<Nullness> store){
  if (accessed == null) {
    return defaultAssumption;
  }
  if (accessed.field.equals("class")) {
    return NONNULL;
  }
  if (accessed.isEnumConstant()) {
    return NONNULL;
  }
  if (accessed.isPrimitive()) {
    return NONNULL;
  }
  if (accessed.hasNonNullConstantValue()) {
    return NONNULL;
  }
  if (accessed.isStatic() && accessed.isFinal()) {
    if (CLASSES_WITH_NON_NULL_CONSTANTS.contains(accessed.clazz)) {
      return NONNULL;
    }
    Nullness initializer=fieldInitializerNullnessIfAvailable(accessed);
    if (initializer != null) {
      return initializer;
    }
  }
  return standardFieldNullness(accessed,path,store);
}
