public static Handler build(ServerConfig serverConfig,Action<? super FileHandlerSpec> config) throws Exception {
  if (!serverConfig.isHasBaseDir()) {
    throw new BaseDirRequiredException("no base dir set for application");
  }
  DefaultFileHandlerSpec spec=new DefaultFileHandlerSpec();
  config.execute(spec);
  Handler handler=new FileHandler(spec.indexFiles,!serverConfig.isDevelopment());
  if (spec.dir != null) {
    handler=Handlers.fileSystem(serverConfig,spec.dir,handler);
  }
  if (spec.path != null) {
    handler=Handlers.prefix(spec.path,handler);
  }
  return handler;
}
