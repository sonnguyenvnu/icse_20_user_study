/** 
 * @param type type
 * @return a DataLinkType object.
 */
public static DataLinkType register(DataLinkType type){
  return registry.put(type.value(),type);
}
