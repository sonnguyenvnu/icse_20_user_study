@Override public ConfigDataBuilder props(String path){
  return add((objectMapper,fileSystemBinding) -> new ByteSourcePropertiesConfigSource(Optional.empty(),Paths2.asByteSource(fileSystemBinding.file(path))).loadConfigData(objectMapper,fileSystemBinding));
}
