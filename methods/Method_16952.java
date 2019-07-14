protected TypeName valueReferenceType(){
  checkState(!context.generateFeatures.contains(Feature.STRONG_VALUES));
  String clazz=context.generateFeatures.contains(Feature.WEAK_VALUES) ? "WeakValueReference" : "SoftValueReference";
  return ParameterizedTypeName.get(ClassName.get(PACKAGE_NAME + ".References",clazz),vTypeVar);
}
