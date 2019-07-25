/** 
 * Fluent factory for constructing a new instance that uses specified TimeZone. Note that timezone used with also be assigned to configured  {@link DateFormat}, changing time formatting defaults.
 */
public BaseSettings with(TimeZone tz){
  if (tz == null) {
    throw new IllegalArgumentException();
  }
  if (tz == _timeZone) {
    return this;
  }
  DateFormat df=_force(_dateFormat,tz);
  return new BaseSettings(_annotationIntrospector,_propertyNamingStrategy,_defaultTyper,_typeValidator,df,_handlerInstantiator,_locale,tz,_defaultBase64,_nodeFactory);
}
