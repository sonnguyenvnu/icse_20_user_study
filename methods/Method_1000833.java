public BaseSettings with(HandlerInstantiator hi){
  if (_handlerInstantiator == hi) {
    return this;
  }
  return new BaseSettings(_annotationIntrospector,_propertyNamingStrategy,_defaultTyper,_typeValidator,_dateFormat,hi,_locale,_timeZone,_defaultBase64,_nodeFactory);
}
