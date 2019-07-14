public WireMockConfiguration extensions(Class<? extends Extension>... classes){
  extensions.putAll(ExtensionLoader.load(classes));
  return this;
}
