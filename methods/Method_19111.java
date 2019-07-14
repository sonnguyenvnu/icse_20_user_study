private static <S extends SpecModel & HasService>void addServiceAwareDelegateMethodDescriptions(Map<Class<? extends Annotation>,DelegateMethodDescription> map,S specModel){
  final MethodParamModel serviceParam=specModel.getServiceParam();
  if (serviceParam == null) {
    map.putAll(SERVICE_AWARE_DELEGATE_METHODS_MAP);
    return;
  }
  for (  Map.Entry<Class<? extends Annotation>,DelegateMethodDescription> entry : SERVICE_AWARE_DELEGATE_METHODS_MAP.entrySet()) {
    DelegateMethodDescription methodDescription=entry.getKey().equals(OnCreateService.class) ? getOnCreateServiceDelegateMethodDescription(entry.getValue(),specModel) : DelegateMethodDescription.fromDelegateMethodDescription(entry.getValue()).optionalParameters(ImmutableList.of(serviceParam)).build();
    map.put(entry.getKey(),methodDescription);
  }
}
