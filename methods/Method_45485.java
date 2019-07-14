private static ExtensionLoader<Serializer> buildLoader(){
  return ExtensionLoaderFactory.getExtensionLoader(Serializer.class,new ExtensionLoaderListener<Serializer>(){
    @Override public void onLoad(    ExtensionClass<Serializer> extensionClass){
      TYPE_SERIALIZER_MAP.put(extensionClass.getCode(),extensionClass.getExtInstance());
      TYPE_CODE_MAP.put(extensionClass.getAlias(),extensionClass.getCode());
    }
  }
);
}
