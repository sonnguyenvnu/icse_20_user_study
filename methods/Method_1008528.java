/** 
 * For BWC: Parse type from type name.
 */
public GeoBoundingBoxQueryBuilder type(String type){
  this.type=GeoExecType.fromString(type);
  return this;
}
