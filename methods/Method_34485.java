private GenericSetterBuilder createGenericSetterBuilder(MetaHolder metaHolder){
  GenericSetterBuilder.Builder setterBuilder=GenericSetterBuilder.builder().groupKey(metaHolder.getCommandGroupKey()).threadPoolKey(metaHolder.getThreadPoolKey()).commandKey(metaHolder.getCommandKey()).collapserKey(metaHolder.getCollapserKey()).commandProperties(metaHolder.getCommandProperties()).threadPoolProperties(metaHolder.getThreadPoolProperties()).collapserProperties(metaHolder.getCollapserProperties());
  if (metaHolder.isCollapserAnnotationPresent()) {
    setterBuilder.scope(metaHolder.getHystrixCollapser().scope());
  }
  return setterBuilder.build();
}
