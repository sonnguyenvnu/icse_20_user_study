private Object createReferenceProxy(SofaReference sofaReferenceAnnotation,Class<?> interfaceType){
  Reference reference=new ReferenceImpl(sofaReferenceAnnotation.uniqueId(),interfaceType,InterfaceMode.annotation,sofaReferenceAnnotation.jvmFirst());
  BindingConverter bindingConverter=bindingConverterFactory.getBindingConverter(new BindingType(sofaReferenceAnnotation.binding().bindingType()));
  if (bindingConverter == null) {
    throw new ServiceRuntimeException("Can not found binding converter for binding type " + sofaReferenceAnnotation.binding().bindingType());
  }
  BindingConverterContext bindingConverterContext=new BindingConverterContext();
  bindingConverterContext.setInBinding(true);
  bindingConverterContext.setApplicationContext(applicationContext);
  bindingConverterContext.setAppName(sofaRuntimeContext.getAppName());
  bindingConverterContext.setAppClassLoader(sofaRuntimeContext.getAppClassLoader());
  Binding binding=bindingConverter.convert(sofaReferenceAnnotation,sofaReferenceAnnotation.binding(),bindingConverterContext);
  reference.addBinding(binding);
  return ReferenceRegisterHelper.registerReference(reference,bindingAdapterFactory,sofaRuntimeContext);
}
