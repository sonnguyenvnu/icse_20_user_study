@Override public boolean equals(final Object object){
  if (this == object) {
    return true;
  }
  if (this.getClass() != object.getClass()) {
    return false;
  }
  JulianDate stamp=(JulianDate)object;
  return (stamp.integer == this.integer) && (Double.compare(stamp.fraction,this.fraction) == 0);
}
