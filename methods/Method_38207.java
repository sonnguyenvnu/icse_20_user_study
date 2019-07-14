/** 
 * Sets ID value for given entity.
 */
public void setIdValue(final E object,final Object value){
  final String propertyName=getIdPropertyName();
  BeanUtil.declared.setProperty(object,propertyName,value);
}
