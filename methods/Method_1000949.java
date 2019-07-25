protected final ObjectReader _with(DeserializationConfig newConfig){
  if (newConfig == _config) {
    return this;
  }
  return _new(this,newConfig);
}
