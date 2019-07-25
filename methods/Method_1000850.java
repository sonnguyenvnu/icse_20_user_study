public B enable(StreamWriteFeature... features){
  for (  StreamWriteFeature f : features) {
    _streamWriteFeatures|=f.getMask();
  }
  return _this();
}
