@Override protected void createBitmaps(){
  super.createBitmaps();
  alphaPatternPaint.setShader(PaintBuilder.createAlphaPatternShader(barHeight / 2));
}
