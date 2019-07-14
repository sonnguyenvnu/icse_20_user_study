@Override public List<MethodMetadata> parseAndValidatateMetadata(Class<?> targetType){
  List<MethodMetadata> metadatas=delegate.parseAndValidatateMetadata(targetType);
  metadatas.forEach(metadata -> METADATA_MAP.put(targetType.getName() + metadata.configKey(),metadata));
  return metadatas;
}
