private void resolveBinding(){
  Object target=service.getTarget();
  if (target == null) {
    throw new ServiceRuntimeException("Must contains the target object whiling registering Service.");
  }
  if (service.hasBinding()) {
    Set<Binding> bindings=service.getBindings();
    boolean allPassed=true;
    for (    Binding binding : bindings) {
      BindingAdapter<Binding> bindingAdapter=this.bindingAdapterFactory.getBindingAdapter(binding.getBindingType());
      if (bindingAdapter == null) {
        throw new ServiceRuntimeException("Can't find BindingAdapter of type " + binding.getBindingType() + " while registering service " + service + ".");
      }
      SofaLogger.info(" <<PreOut Binding [{0}] Begins - {1}.",binding.getBindingType(),service);
      try {
        bindingAdapter.preOutBinding(service,binding,target,getContext());
      }
 catch (      Throwable t) {
        allPassed=false;
        SofaLogger.error(t," <<PreOut Binding [{0}] for [{1}] occur exception.",binding.getBindingType(),service);
        continue;
      }
      SofaLogger.info(" <<PreOut Binding [{0}] Ends - {1}.",binding.getBindingType(),service);
    }
    if (!allPassed) {
      throw new ServiceRuntimeException(" <<PreOut Binding [" + service + "] occur exception.");
    }
  }
}
