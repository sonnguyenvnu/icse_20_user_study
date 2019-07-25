/** 
 * {@inheritDoc} 
 */
@Override public boolean matches(Field field){
  return expectedFieldType.isAssignableFrom(field.getType());
}
