public static CombinedDrawable createCircleDrawableWithIcon(int size,Drawable drawable,int stroke){
  OvalShape ovalShape=new OvalShape();
  ovalShape.resize(size,size);
  ShapeDrawable defaultDrawable=new ShapeDrawable(ovalShape);
  Paint paint=defaultDrawable.getPaint();
  paint.setColor(0xffffffff);
  if (stroke == 1) {
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(AndroidUtilities.dp(2));
  }
 else   if (stroke == 2) {
    paint.setAlpha(0);
  }
  CombinedDrawable combinedDrawable=new CombinedDrawable(defaultDrawable,drawable);
  combinedDrawable.setCustomSize(size,size);
  return combinedDrawable;
}
