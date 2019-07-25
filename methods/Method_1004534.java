/** 
 * {@inheritDoc} 
 */
@Override public boolean matches(Field field){
  return fieldName.equals(field.getName()) && fieldType.equals(field.getType());
}
