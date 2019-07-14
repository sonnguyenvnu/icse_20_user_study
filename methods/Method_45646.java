private static ExtensionLoader<Protocol> buildLoader(){
  return ExtensionLoaderFactory.getExtensionLoader(Protocol.class,new ExtensionLoaderListener<Protocol>(){
    @Override public void onLoad(    ExtensionClass<Protocol> extensionClass){
      Protocol protocol=extensionClass.getExtInstance();
      TYPE_PROTOCOL_MAP.put(extensionClass.getCode(),protocol);
      TYPE_CODE_MAP.put(extensionClass.getAlias(),extensionClass.getCode());
      if (RpcConfigs.getBooleanValue(RpcOptions.TRANSPORT_SERVER_PROTOCOL_ADAPTIVE)) {
        maxMagicOffset=2;
        registerAdaptiveProtocol(protocol.protocolInfo());
      }
    }
  }
);
}
