@Nullable static MethodParamModel createServiceParam(SpecModel model){
  final TypeSpec serviceType=extractServiceParam(model);
  if (serviceType == null) {
    return null;
  }
  return MethodParamModelFactory.createSimpleMethodParamModel(serviceType,"_service",model.getRepresentedObject());
}
