/** 
 * Gets an instance of UnsupportedDurationField for a specific named field. The returned instance is cached.
 * @param type  the type to obtain
 * @return the instance
 */
public static synchronized UnsupportedDurationField getInstance(DurationFieldType type){
  UnsupportedDurationField field;
  if (cCache == null) {
    cCache=new HashMap<DurationFieldType,UnsupportedDurationField>(7);
    field=null;
  }
 else {
    field=cCache.get(type);
  }
  if (field == null) {
    field=new UnsupportedDurationField(type);
    cCache.put(type,field);
  }
  return field;
}
