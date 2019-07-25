@Override public double lat(){
  return int2coord((int)(this.latlon >>> 32));
}
