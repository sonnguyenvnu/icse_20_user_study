public BaseSettings with(TypeResolverBuilder<?> typer){
  if (_defaultTyper == typer) {
    return this;
  }
  return new BaseSettings(_annotationIntrospector,_propertyNamingStrategy,typer,_typeValidator,_dateFormat,_handlerInstantiator,_locale,_timeZone,_defaultBase64,_nodeFactory);
}
