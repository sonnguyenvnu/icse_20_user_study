protected List<Binding> parseBindings(List<Element> parseElements,ApplicationContext appContext,boolean isInBinding){
  List<Binding> result=new ArrayList<>();
  if (parseElements != null) {
    for (    Element element : parseElements) {
      String tagName=element.getLocalName();
      BindingConverter bindingConverter=bindingConverterFactory.getBindingConverterByTagName(tagName);
      if (bindingConverter == null) {
        dealWithbindingConverterNotExist(tagName);
        continue;
      }
      BindingConverterContext bindingConverterContext=new BindingConverterContext();
      bindingConverterContext.setInBinding(isInBinding);
      bindingConverterContext.setApplicationContext(appContext);
      bindingConverterContext.setAppName(sofaRuntimeContext.getAppName());
      bindingConverterContext.setAppClassLoader(sofaRuntimeContext.getAppClassLoader());
      bindingConverterContext.setRepeatReferLimit(repeatReferLimit);
      bindingConverterContext.setBeanId(beanId);
      setProperties(bindingConverterContext);
      Binding binding=bindingConverter.convert(element,bindingConverterContext);
      result.add(binding);
    }
  }
  return result;
}
