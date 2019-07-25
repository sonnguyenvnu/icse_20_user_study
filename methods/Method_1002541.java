/** 
 * Add the key with the given name and value to the CompoundKey.
 * @param name name of the key
 * @param value value of the key
 * @param typeInfo TypeInfo for the value
 * @return this
 */
public CompoundKey append(String name,Object value,TypeInfo typeInfo){
  if (_isReadOnly) {
    throw new UnsupportedOperationException("Can't append to a read only key!");
  }
  if (name == null) {
    throw new IllegalArgumentException("name of CompoundKey part cannot be null");
  }
  if (value == null) {
    throw new IllegalArgumentException("value of CompoundKey part cannot be null");
  }
  if (typeInfo == null) {
    throw new IllegalArgumentException("typeInfo of CompoundKey part cannot be null");
  }
  _keys.put(name,new ValueAndTypeInfoPair(value,typeInfo));
  return this;
}
