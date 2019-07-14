@Nullable private static TypeSpec extractServiceParam(SpecModel specModel){
  final SpecMethodModel<DelegateMethod,Void> onCreateService=SpecModelUtils.getMethodModelWithAnnotation(specModel,OnCreateService.class);
  return onCreateService == null ? null : onCreateService.returnTypeSpec;
}
