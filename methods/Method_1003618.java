@Override public ConfigDataBuilder props(Path path){
  return add(new ByteSourcePropertiesConfigSource(Optional.empty(),Paths2.asByteSource(path)));
}
