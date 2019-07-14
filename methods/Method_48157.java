private static Map<String,IndexProvider> getIndexes(Configuration config){
  ImmutableMap.Builder<String,IndexProvider> builder=ImmutableMap.builder();
  for (  String index : config.getContainedNamespaces(INDEX_NS)) {
    Preconditions.checkArgument(StringUtils.isNotBlank(index),"Invalid index name [%s]",index);
    log.info("Configuring index [{}]",index);
    IndexProvider provider=getImplementationClass(config.restrictTo(index),config.get(INDEX_BACKEND,index),StandardIndexProvider.getAllProviderClasses());
    Preconditions.checkNotNull(provider);
    builder.put(index,provider);
  }
  return builder.build();
}
