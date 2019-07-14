@NonNull Map<String,Object> combinedProperties(final @NonNull Map<String,Object> additionalProperties){
  final Map<String,Object> combinedProperties=new HashMap<>(additionalProperties);
  combinedProperties.putAll(defaultProperties());
  return combinedProperties;
}
