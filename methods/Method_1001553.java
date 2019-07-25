private static Atom blank(final Atom header){
  if (header == null) {
    return null;
  }
  return new AbstractAtom(){
    public Dimension2D calculateDimension(    StringBounder stringBounder){
      return header.calculateDimension(stringBounder);
    }
    public double getStartingAltitude(    StringBounder stringBounder){
      return header.getStartingAltitude(stringBounder);
    }
    public void drawU(    UGraphic ug){
    }
  }
;
}
