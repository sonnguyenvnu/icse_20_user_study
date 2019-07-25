/** 
 * Takes a mapping tree and returns a map of all of its fields flattened, and paired with their field types.
 */
public Map<String,FieldType> flatten(){
  if (fields == null || fields.length == 0) {
    return Collections.<String,FieldType>emptyMap();
  }
  Map<String,FieldType> map=new LinkedHashMap<String,FieldType>();
  for (  Field nestedField : fields) {
    addSubFieldToMap(map,nestedField,null);
  }
  return map;
}
