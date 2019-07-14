private Object createProxy(Reference reference,Binding binding){
  BindingAdapter<Binding> bindingAdapter=bindingAdapterFactory.getBindingAdapter(binding.getBindingType());
  if (bindingAdapter == null) {
    throw new ServiceRuntimeException("Can't find BindingAdapter of type " + binding.getBindingType() + " for reference " + reference + ".");
  }
  SofaLogger.info(" >>In Binding [{0}] Begins - {1}.",binding.getBindingType(),reference);
  Object proxy;
  try {
    proxy=bindingAdapter.inBinding(reference,binding,sofaRuntimeContext);
  }
  finally {
    SofaLogger.info(" >>In Binding [{0}] Ends - {1}.",binding.getBindingType(),reference);
  }
  return proxy;
}
