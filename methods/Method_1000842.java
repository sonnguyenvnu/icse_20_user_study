public B disable(MapperFeature... features){
  for (  MapperFeature f : features) {
    _mapperFeatures&=~f.getMask();
  }
  return _this();
}
