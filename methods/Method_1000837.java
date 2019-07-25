public BaseSettings with(JsonNodeFactory nodeFactory){
  if (nodeFactory == _nodeFactory) {
    return this;
  }
  return new BaseSettings(_annotationIntrospector,_propertyNamingStrategy,_defaultTyper,_typeValidator,_dateFormat,_handlerInstantiator,_locale,_timeZone,_defaultBase64,nodeFactory);
}
