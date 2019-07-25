public B configure(StreamReadFeature feature,boolean state){
  if (state) {
    _streamReadFeatures|=feature.getMask();
  }
 else {
    _streamReadFeatures&=~feature.getMask();
  }
  return _this();
}
