public B disable(StreamReadFeature... features){
  for (  StreamReadFeature f : features) {
    _streamReadFeatures&=~f.getMask();
  }
  return _this();
}
