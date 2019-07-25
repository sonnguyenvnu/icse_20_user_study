/** 
 * {@inheritDoc} 
 */
@Override public boolean matches(Field field){
  return expectedFieldType.equals(field.getType());
}
