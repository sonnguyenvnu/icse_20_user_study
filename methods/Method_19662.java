void updateBackground(){
  final float strokeWidth=getDimension(R.dimen.fab_stroke_width);
  final float halfStrokeWidth=strokeWidth / 2f;
  LayerDrawable layerDrawable=new LayerDrawable(new Drawable[]{getResources().getDrawable(mSize == SIZE_NORMAL ? R.drawable.fab_bg_normal : R.drawable.fab_bg_mini),createFillDrawable(strokeWidth),createOuterStrokeDrawable(strokeWidth),getIconDrawable()});
  int iconOffset=(int)(mCircleSize - getDimension(R.dimen.fab_icon_size)) / 2;
  int circleInsetHorizontal=(int)(mShadowRadius);
  int circleInsetTop=(int)(mShadowRadius - mShadowOffset);
  int circleInsetBottom=(int)(mShadowRadius + mShadowOffset);
  layerDrawable.setLayerInset(1,circleInsetHorizontal,circleInsetTop,circleInsetHorizontal,circleInsetBottom);
  layerDrawable.setLayerInset(2,(int)(circleInsetHorizontal - halfStrokeWidth),(int)(circleInsetTop - halfStrokeWidth),(int)(circleInsetHorizontal - halfStrokeWidth),(int)(circleInsetBottom - halfStrokeWidth));
  layerDrawable.setLayerInset(3,circleInsetHorizontal + iconOffset,circleInsetTop + iconOffset,circleInsetHorizontal + iconOffset,circleInsetBottom + iconOffset);
  setBackgroundCompat(layerDrawable);
}
