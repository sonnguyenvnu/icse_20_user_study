@Override public double lon(){
  return int2coord((int)(this.latlon & (Integer.MAX_VALUE)));
}
