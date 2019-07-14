private void activateBinding(){
  Object target=service.getTarget();
  if (target == null) {
    throw new ServiceRuntimeException("Must contains the target object whiling registering Service.");
  }
  if (service.hasBinding()) {
    boolean allPassed=true;
    Set<Binding> bindings=service.getBindings();
    for (    Binding binding : bindings) {
      BindingAdapter<Binding> bindingAdapter=this.bindingAdapterFactory.getBindingAdapter(binding.getBindingType());
      if (bindingAdapter == null) {
        throw new ServiceRuntimeException("Can't find BindingAdapter of type " + binding.getBindingType() + " while registering service " + service + ".");
      }
      Object outBindingResult;
      SofaLogger.info(" <<Out Binding [{0}] Begins - {1}.",binding.getBindingType(),service);
      try {
        outBindingResult=bindingAdapter.outBinding(service,binding,target,getContext());
      }
 catch (      Throwable t) {
        allPassed=false;
        SofaLogger.error(t," <<Out binding [{0}] for [{1}] occur exception.",binding.getBindingType(),service);
        continue;
      }
      if (!Boolean.FALSE.equals(outBindingResult)) {
        SofaLogger.info(" <<Out Binding [{0}] Ends - {1}.",binding.getBindingType(),service);
      }
 else {
        binding.setHealthy(false);
        SofaLogger.info(" <<Out Binding [{0}] Fails, Don't publish service - {1}.",binding.getBindingType(),service);
      }
    }
    if (!allPassed) {
      throw new ServiceRuntimeException(" <<Out Binding [" + service + "] occur exception.");
    }
  }
  SofaLogger.info("Register Service - {0}",service);
}
