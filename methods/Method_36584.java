private List<Binding> getSofaServiceBinding(SofaService sofaServiceAnnotation,SofaServiceBinding[] sofaServiceBindings){
  List<Binding> bindings=new ArrayList<>();
  for (  SofaServiceBinding sofaServiceBinding : sofaServiceBindings) {
    BindingConverter bindingConverter=bindingConverterFactory.getBindingConverter(new BindingType(sofaServiceBinding.bindingType()));
    if (bindingConverter == null) {
      throw new ServiceRuntimeException("Can not found binding converter for binding type " + sofaServiceBinding.bindingType());
    }
    BindingConverterContext bindingConverterContext=new BindingConverterContext();
    bindingConverterContext.setInBinding(false);
    bindingConverterContext.setApplicationContext(applicationContext);
    bindingConverterContext.setAppName(sofaRuntimeContext.getAppName());
    bindingConverterContext.setAppClassLoader(sofaRuntimeContext.getAppClassLoader());
    Binding binding=bindingConverter.convert(sofaServiceAnnotation,sofaServiceBinding,bindingConverterContext);
    bindings.add(binding);
  }
  return bindings;
}
