@Override public ConfigDataBuilder props(ByteSource byteSource){
  return add(new ByteSourcePropertiesConfigSource(Optional.empty(),byteSource));
}
