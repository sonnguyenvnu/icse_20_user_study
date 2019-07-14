private Drawable createInnerStrokesDrawable(final int color,float strokeWidth){
  if (!mStrokeVisible) {
    return new ColorDrawable(Color.TRANSPARENT);
  }
  ShapeDrawable shapeDrawable=new ShapeDrawable(new OvalShape());
  final int bottomStrokeColor=darkenColor(color);
  final int bottomStrokeColorHalfTransparent=halfTransparent(bottomStrokeColor);
  final int topStrokeColor=lightenColor(color);
  final int topStrokeColorHalfTransparent=halfTransparent(topStrokeColor);
  final Paint paint=shapeDrawable.getPaint();
  paint.setAntiAlias(true);
  paint.setStrokeWidth(strokeWidth);
  paint.setStyle(Style.STROKE);
  shapeDrawable.setShaderFactory(new ShaderFactory(){
    @Override public Shader resize(    int width,    int height){
      return new LinearGradient(width / 2,0,width / 2,height,new int[]{topStrokeColor,topStrokeColorHalfTransparent,color,bottomStrokeColorHalfTransparent,bottomStrokeColor},new float[]{0f,0.2f,0.5f,0.8f,1f},TileMode.CLAMP);
    }
  }
);
  return shapeDrawable;
}
