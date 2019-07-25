public static UriHandler parse(Object target,boolean exported,UriInterceptor... interceptors){
  UriHandler handler=toHandler(target);
  if (handler != null) {
    if (!exported) {
      handler.addInterceptor(NotExportedInterceptor.INSTANCE);
    }
    handler.addInterceptors(interceptors);
  }
  return handler;
}
