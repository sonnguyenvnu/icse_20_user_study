public BoundingBox clone(){
  return new BoundingBox(this.mLatNorth,this.mLonEast,this.mLatSouth,this.mLonWest);
}
