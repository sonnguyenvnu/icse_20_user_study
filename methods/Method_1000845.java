public B disable(DeserializationFeature... features){
  for (  DeserializationFeature f : features) {
    _deserFeatures&=~f.getMask();
  }
  return _this();
}
