@Override protected JulianDate clone(){
  return new JulianDate(this.integer,this.fraction);
}
