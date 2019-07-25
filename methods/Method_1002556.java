public static ResourceMethodConfigProvider build(RestLiMethodConfig config){
  try {
    RestLiMethodConfigBuilder builder=new RestLiMethodConfigBuilder();
    builder.addConfig(ResourceMethodConfigProviderImpl.DEFAULT_CONFIG);
    builder.addConfig(config);
    return new ResourceMethodConfigProviderImpl(builder.build());
  }
 catch (  ResourceMethodConfigParsingException e) {
    throw new RuntimeException(e);
  }
}
