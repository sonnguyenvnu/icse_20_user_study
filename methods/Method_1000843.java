public B disable(SerializationFeature... features){
  for (  SerializationFeature f : features) {
    _serFeatures&=~f.getMask();
  }
  return _this();
}
