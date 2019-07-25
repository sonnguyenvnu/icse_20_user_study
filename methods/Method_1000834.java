public BaseSettings with(Locale l){
  if (_locale == l) {
    return this;
  }
  return new BaseSettings(_annotationIntrospector,_propertyNamingStrategy,_defaultTyper,_typeValidator,_dateFormat,_handlerInstantiator,l,_timeZone,_defaultBase64,_nodeFactory);
}
