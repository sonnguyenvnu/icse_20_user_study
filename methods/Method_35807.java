public WireMockConfiguration extensions(Extension... extensionInstances){
  extensions.putAll(ExtensionLoader.asMap(asList(extensionInstances)));
  return this;
}
