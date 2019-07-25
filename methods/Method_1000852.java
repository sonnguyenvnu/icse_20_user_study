public B configure(StreamWriteFeature feature,boolean state){
  if (state) {
    _streamWriteFeatures|=feature.getMask();
  }
 else {
    _streamWriteFeatures&=~feature.getMask();
  }
  return _this();
}
