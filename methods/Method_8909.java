private void setStroke(boolean stroke){
  selectedStroke=stroke;
  if (currentEntityView instanceof TextPaintView) {
    Swatch currentSwatch=colorPicker.getSwatch();
    if (stroke && currentSwatch.color == Color.WHITE) {
      Swatch blackSwatch=new Swatch(Color.BLACK,0.85f,currentSwatch.brushWeight);
      setCurrentSwatch(blackSwatch,true);
    }
 else     if (!stroke && currentSwatch.color == Color.BLACK) {
      Swatch blackSwatch=new Swatch(Color.WHITE,1.0f,currentSwatch.brushWeight);
      setCurrentSwatch(blackSwatch,true);
    }
    ((TextPaintView)currentEntityView).setStroke(stroke);
  }
}
