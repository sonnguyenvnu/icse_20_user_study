private void addProxyMapping(final String baseUrl){
  wireMockServer.loadMappingsUsing(new MappingsLoader(){
    @Override public void loadMappingsInto(    StubMappings stubMappings){
      RequestPattern requestPattern=newRequestPattern(ANY,anyUrl()).build();
      ResponseDefinition responseDef=responseDefinition().proxiedFrom(baseUrl).build();
      StubMapping proxyBasedMapping=new StubMapping(requestPattern,responseDef);
      proxyBasedMapping.setPriority(10);
      stubMappings.addMapping(proxyBasedMapping);
    }
  }
);
}
