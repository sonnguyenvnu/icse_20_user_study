public Settings load(String source){
  Properties copy=IOUtils.propsFromString(source);
  merge(copy);
  return this;
}
