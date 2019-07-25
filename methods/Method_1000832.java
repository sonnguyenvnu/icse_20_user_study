public BaseSettings with(DateFormat df){
  if (_dateFormat == df) {
    return this;
  }
  if ((df != null) && hasExplicitTimeZone()) {
    df=_force(df,_timeZone);
  }
  return new BaseSettings(_annotationIntrospector,_propertyNamingStrategy,_defaultTyper,_typeValidator,df,_handlerInstantiator,_locale,_timeZone,_defaultBase64,_nodeFactory);
}
