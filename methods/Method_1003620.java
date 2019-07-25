@Override public ConfigDataBuilder yaml(ByteSource byteSource){
  return add(new YamlConfigSource(byteSource));
}
