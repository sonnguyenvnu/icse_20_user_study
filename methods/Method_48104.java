/** 
 * Whether this geometry has any points in common with the given geometry.
 * @param other
 * @return
 */
public boolean intersect(Geoshape other){
  SpatialRelation r=getSpatialRelation(other);
  return r == SpatialRelation.INTERSECTS || r == SpatialRelation.CONTAINS || r == SpatialRelation.WITHIN;
}
