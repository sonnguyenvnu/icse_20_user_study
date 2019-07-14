private void updateColor(){
  if (stroke) {
    editText.setTextColor(0xffffffff);
    editText.setStrokeColor(swatch.color);
    editText.setShadowLayer(0,0,0,0);
  }
 else {
    editText.setTextColor(swatch.color);
    editText.setStrokeColor(Color.TRANSPARENT);
    editText.setShadowLayer(8,0,2,0xaa000000);
  }
}
