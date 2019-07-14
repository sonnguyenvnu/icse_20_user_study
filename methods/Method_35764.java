public StubRequestHandler buildStubRequestHandler(){
  Map<String,PostServeAction> postServeActions=options.extensionsOfType(PostServeAction.class);
  return new StubRequestHandler(this,new StubResponseRenderer(options.filesRoot().child(FILES_ROOT),getGlobalSettingsHolder(),new ProxyResponseRenderer(options.proxyVia(),options.httpsSettings().trustStore(),options.shouldPreserveHostHeader(),options.proxyHostHeader(),globalSettingsHolder),ImmutableList.copyOf(options.extensionsOfType(ResponseTransformer.class).values())),this,postServeActions,requestJournal,getStubRequestFilters());
}
