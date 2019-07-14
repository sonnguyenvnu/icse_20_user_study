@Override public void addBindingAdapters(Set<BindingAdapter> bindingAdapters){
  if (bindingAdapters == null || bindingAdapters.size() == 0) {
    return;
  }
  for (  BindingAdapter bindingAdapter : bindingAdapters) {
    bindingTypeBindingAdapterMap.put(bindingAdapter.getBindingType(),bindingAdapter);
  }
}
