private static ExtensionLoader<TelnetHandler> buildLoader(){
  return ExtensionLoaderFactory.getExtensionLoader(TelnetHandler.class,new ExtensionLoaderListener<TelnetHandler>(){
    @Override public void onLoad(    ExtensionClass<TelnetHandler> extensionClass){
      TelnetHandler handler=extensionClass.getExtInstance();
      supportedCmds.put(handler.getCommand(),handler);
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Add telnet handler {}:{}.",handler.getCommand(),handler);
      }
    }
  }
);
}
