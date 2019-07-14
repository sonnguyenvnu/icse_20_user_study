@Override public StubMapping apply(ServeEvent event){
  final RequestPattern requestPattern=requestTransformer.apply(event.getRequest()).build();
  final ResponseDefinition responseDefinition=responseTransformer.apply(event.getResponse());
  StubMapping stubMapping=new StubMapping(requestPattern,responseDefinition);
  URI uri=URI.create(event.getRequest().getUrl());
  stubMapping.setName(SafeNames.makeSafeNameFromUrl(uri.getPath()));
  return stubMapping;
}
