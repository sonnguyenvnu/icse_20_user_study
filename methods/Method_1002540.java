/** 
 * Add the key with the given name and value to the CompoundKey. Only primitive values are supported.  The  {@link CompoundKey.TypeInfo} will be generated based on the value of the key.
 * @param name name of the key
 * @param value value of the key
 * @return this
 */
public CompoundKey append(String name,Object value){
  if (value == null) {
    throw new IllegalArgumentException("value of CompoundKey part cannot be null");
  }
  TypeInfo typeInfo=new CompoundKey.TypeInfo(value.getClass(),value.getClass());
  append(name,value,typeInfo);
  return this;
}
