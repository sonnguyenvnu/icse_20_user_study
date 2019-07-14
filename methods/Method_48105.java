/** 
 * Whether this geometry contains the given geometry.
 * @param outer
 * @return
 */
public boolean contains(Geoshape outer){
  return getSpatialRelation(outer) == SpatialRelation.CONTAINS;
}
