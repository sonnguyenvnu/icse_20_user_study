/** 
 * @return the BoundingBox enclosing this BoundingBox and bb2 BoundingBox 
 */
public BoundingBox concat(BoundingBox bb2){
  return new BoundingBox(Math.max(this.mLatNorth,bb2.getLatNorth()),Math.max(this.mLonEast,bb2.getLonEast()),Math.min(this.mLatSouth,bb2.getLatSouth()),Math.min(this.mLonWest,bb2.getLonWest()));
}
