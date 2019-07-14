/** 
 * Returns unique key for this entity. Returned key is built from entity class and id value.
 */
public String getKeyValue(final E object){
  Object idValue=getIdValue(object);
  String idValueString=idValue == null ? StringPool.NULL : idValue.toString();
  return type.getName().concat(StringPool.COLON).concat(idValueString);
}
