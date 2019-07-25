@Override public void intercept(Chain chain) throws Exception {
  Uri uri=chain.request().uri;
  String scheme=uri.getScheme();
  if (ModuleConfig.HTTP_SCHEME.equalsIgnoreCase(scheme) || ModuleConfig.HTTPS_SCHEME.equalsIgnoreCase(scheme)) {
    RouterRequest newRequest=chain.request().toBuilder().scheme(ModuleConfig.APP_SCHEME).host(ModuleConfig.Help.NAME).path(ModuleConfig.Help.WEB).putString("data",uri.toString()).build();
    chain.proceed(newRequest);
  }
 else {
    chain.proceed(chain.request());
  }
}
