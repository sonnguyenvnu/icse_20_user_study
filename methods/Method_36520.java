@Override public void addBindingConverters(Set<BindingConverter> bindingConverters){
  if (bindingConverters == null || bindingConverters.size() == 0) {
    return;
  }
  for (  BindingConverter bindingConverter : bindingConverters) {
    bindingTypeBindingConverterMap.put(bindingConverter.supportBindingType(),bindingConverter);
    tagBindingConverterMap.put(bindingConverter.supportTagName(),bindingConverter);
  }
}
