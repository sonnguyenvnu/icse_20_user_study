public static SpecMethodModel<BindDynamicValueMethod,Void> getBindDelegateMethodForDynamicProp(SpecModel specModel,PropModel prop){
  for (  SpecMethodModel<BindDynamicValueMethod,Void> method : ((MountSpecModel)specModel).getBindDynamicValueMethods()) {
    if (prop.equals(method.methodParams.get(1))) {
      return method;
    }
  }
  return null;
}
