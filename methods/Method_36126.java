@Override @SuppressWarnings("unchecked") public <T extends Extension>Map<String,T> extensionsOfType(final Class<T> extensionType){
  ImmutableMap.Builder<String,T> builder=ImmutableMap.builder();
  if (optionSet.has(EXTENSIONS)) {
    String classNames=(String)optionSet.valueOf(EXTENSIONS);
    builder.putAll((Map<String,T>)Maps.filterEntries(ExtensionLoader.load(classNames.split(",")),valueAssignableFrom(extensionType)));
  }
  if (optionSet.has(GLOBAL_RESPONSE_TEMPLATING) && ResponseDefinitionTransformer.class.isAssignableFrom(extensionType)) {
    ResponseTemplateTransformer transformer=ResponseTemplateTransformer.builder().global(true).maxCacheEntries(getMaxTemplateCacheEntries()).build();
    builder.put(transformer.getName(),(T)transformer);
  }
 else   if (optionSet.has(LOCAL_RESPONSE_TEMPLATING) && ResponseDefinitionTransformer.class.isAssignableFrom(extensionType)) {
    ResponseTemplateTransformer transformer=ResponseTemplateTransformer.builder().global(false).maxCacheEntries(getMaxTemplateCacheEntries()).build();
    builder.put(transformer.getName(),(T)transformer);
  }
  return builder.build();
}
