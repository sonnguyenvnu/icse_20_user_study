@SuppressWarnings("unchecked") private <T>Reference getReferenceFromReferenceParam(ReferenceParam<T> referenceParam){
  BindingParam bindingParam=referenceParam.getBindingParam();
  Reference reference=new ReferenceImpl(referenceParam.getUniqueId(),referenceParam.getInterfaceType(),InterfaceMode.api,referenceParam.isJvmFirst(),null);
  if (bindingParam == null) {
    JvmBindingParam jvmBindingParam=new JvmBindingParam();
    jvmBindingParam.setSerialize(false);
    reference.addBinding(new JvmBinding().setJvmBindingParam(jvmBindingParam));
  }
 else {
    BindingConverter bindingConverter=bindingConverterFactory.getBindingConverter(bindingParam.getBindingType());
    if (bindingConverter == null) {
      throw new ServiceRuntimeException("Can not found binding converter for binding type " + bindingParam.getBindingType());
    }
    BindingConverterContext bindingConverterContext=new BindingConverterContext();
    bindingConverterContext.setInBinding(true);
    bindingConverterContext.setAppName(sofaRuntimeContext.getAppName());
    bindingConverterContext.setAppClassLoader(sofaRuntimeContext.getAppClassLoader());
    Binding binding=bindingConverter.convert(bindingParam,bindingConverterContext);
    reference.addBinding(binding);
  }
  return reference;
}
