protected TypeName keyReferenceType(){
  checkState(context.generateFeatures.contains(Feature.WEAK_KEYS));
  return ParameterizedTypeName.get(ClassName.get(PACKAGE_NAME + ".References","WeakKeyReference"),kTypeVar);
}
