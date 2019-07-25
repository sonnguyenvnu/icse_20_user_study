/** 
 * Add an object to this schema. This method must not be called within CreateSchemaObject; use Database.addSchemaObject() instead
 * @param obj the object to add
 */
public void add(SchemaObject obj){
  if (obj.getSchema() != this) {
    DbException.throwInternalError("wrong schema");
  }
  String name=obj.getName();
  Map<String,SchemaObject> map=getMap(obj.getType());
  if (SysProperties.CHECK && map.get(name) != null) {
    DbException.throwInternalError("object already exists: " + name);
  }
  map.put(name,obj);
  freeUniqueName(name);
}
