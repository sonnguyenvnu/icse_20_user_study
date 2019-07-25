public boolean contains(final double aLatitude,final double aLongitude){
  return ((aLatitude < this.mLatNorth) && (aLatitude > this.mLatSouth)) && ((aLongitude < this.mLonEast) && (aLongitude > this.mLonWest));
}
