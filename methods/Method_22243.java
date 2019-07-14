@NonNull @BuilderMethod public <R extends ConfigurationBuilder>R getPluginConfigurationBuilder(@NonNull Class<R> c){
  for (  ConfigurationBuilder builder : configurationBuilders()) {
    if (c.isAssignableFrom(builder.getClass())) {
      return (R)builder;
    }
  }
  if (c.isInterface()) {
    ACRA.log.w(LOG_TAG,"Couldn't find ConfigurationBuilder " + c.getSimpleName() + ". ALL CALLS TO IT WILL BE IGNORED!");
    return StubCreator.createStub(c,(proxy,method,args) -> proxy);
  }
  throw new IllegalArgumentException("Class " + c.getName() + " is not a registered ConfigurationBuilder");
}
