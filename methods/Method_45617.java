private ExtensionClass<T> buildClass(Extension extension,Class<? extends T> implClass,String alias){
  ExtensionClass<T> extensionClass=new ExtensionClass<T>(implClass,alias);
  extensionClass.setCode(extension.code());
  extensionClass.setSingleton(extensible.singleton());
  extensionClass.setOrder(extension.order());
  extensionClass.setOverride(extension.override());
  extensionClass.setRejection(extension.rejection());
  return extensionClass;
}
