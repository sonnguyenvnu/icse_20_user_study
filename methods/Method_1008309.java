/** 
 * Add a new hole to the polygon
 * @param hole linear ring defining the hole
 * @param coerce if set to true, it will try to close the hole by adding starting point as end point
 * @return this
 */
public PolygonBuilder hole(LineStringBuilder hole,boolean coerce){
  if (coerce) {
    hole.close();
  }
  validateLinearRing(hole);
  holes.add(hole);
  return this;
}
