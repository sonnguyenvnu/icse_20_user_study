/** 
 * Returns the type for the typeName specified.
 * @param typeName String
 * @return Class
 */
public Class<?> typeFor(String typeName){
  return typesByName.get(typeName);
}
