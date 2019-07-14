private static ExtensionLoader<Compressor> buildLoader(){
  return ExtensionLoaderFactory.getExtensionLoader(Compressor.class,new ExtensionLoaderListener<Compressor>(){
    @Override public void onLoad(    ExtensionClass<Compressor> extensionClass){
      TYPE_COMPRESSOR_MAP.put(extensionClass.getCode(),extensionClass.getExtInstance());
      TYPE_CODE_MAP.put(extensionClass.getAlias(),extensionClass.getCode());
    }
  }
);
}
