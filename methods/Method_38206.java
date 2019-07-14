/** 
 * Returns ID value for given entity instance.
 */
public Object getIdValue(final E object){
  final String propertyName=getIdPropertyName();
  return BeanUtil.declared.getProperty(object,propertyName);
}
