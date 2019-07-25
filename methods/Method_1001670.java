public static TextBlock empty(final double width,final double height){
  return new AbstractTextBlock(){
    public void drawU(    UGraphic ug){
    }
    public Dimension2D calculateDimension(    StringBounder stringBounder){
      return new Dimension2DDouble(width,height);
    }
  }
;
}
