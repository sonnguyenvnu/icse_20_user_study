public static LoadPlanable multiply(final LoadPlanable p1,final LoadPlanable p2){
  return new LoadPlanable(){
    public int getLoadAt(    Instant instant){
      return p1.getLoadAt(instant) * p2.getLoadAt(instant) / 100;
    }
  }
;
}
