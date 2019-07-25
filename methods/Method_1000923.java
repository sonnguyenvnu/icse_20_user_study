@Override public StdTypeResolverBuilder init(JsonTypeInfo.Value settings,TypeIdResolver idRes){
  _customIdResolver=idRes;
  if (settings != null) {
    _idType=settings.getIdType();
    if (_idType == null) {
      throw new IllegalArgumentException("idType cannot be null");
    }
    _includeAs=settings.getInclusionType();
    _typeProperty=settings.getPropertyName();
    if (_typeProperty == null) {
      _typeProperty=_idType.getDefaultPropertyName();
    }
    _typeIdVisible=settings.getIdVisible();
    _defaultImpl=settings.getDefaultImpl();
  }
  return this;
}
