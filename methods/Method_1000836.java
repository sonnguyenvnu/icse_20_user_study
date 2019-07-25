public BaseSettings with(Base64Variant base64){
  if (base64 == _defaultBase64) {
    return this;
  }
  return new BaseSettings(_annotationIntrospector,_propertyNamingStrategy,_defaultTyper,_typeValidator,_dateFormat,_handlerInstantiator,_locale,_timeZone,base64,_nodeFactory);
}
