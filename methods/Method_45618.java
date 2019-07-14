private void loadSuccess(String alias,ExtensionClass<T> extensionClass){
  if (listener != null) {
    try {
      listener.onLoad(extensionClass);
      all.put(alias,extensionClass);
    }
 catch (    Exception e) {
      LOGGER.error("Error when load extension of extensible " + interfaceClass + " with alias: " + alias + ".",e);
    }
  }
 else {
    all.put(alias,extensionClass);
  }
}
