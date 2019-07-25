public B configure(DeserializationFeature feature,boolean state){
  if (state) {
    _deserFeatures|=feature.getMask();
  }
 else {
    _deserFeatures&=~feature.getMask();
  }
  return _this();
}
