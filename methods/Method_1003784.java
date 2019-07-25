public GroovyChain apply(List<Handler> storage){
  return new DefaultGroovyChain(new DefaultChain(storage,serverConfig,registry));
}
