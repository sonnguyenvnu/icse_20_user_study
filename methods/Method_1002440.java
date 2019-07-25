/** 
 * Returns whether the fieldName is a member of the record.
 * @param fieldName to check.
 * @return true if fieldName is a member of the record.
 */
public boolean contains(String fieldName){
  return _fieldNameToIndexMap.containsKey(fieldName);
}
