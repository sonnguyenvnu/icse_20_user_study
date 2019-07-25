public boolean intersects(List<MyCurve> others){
  for (  MyCurve other : others) {
    if (this.intersects(other)) {
      return true;
    }
  }
  return false;
}
