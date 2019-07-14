void updateBackground(){
  LayerDrawable layerDrawable;
  if (hasShadow()) {
    layerDrawable=new LayerDrawable(new Drawable[]{new Shadow(),createFillDrawable(),getIconDrawable()});
  }
 else {
    layerDrawable=new LayerDrawable(new Drawable[]{createFillDrawable(),getIconDrawable()});
  }
  int iconSize=-1;
  if (getIconDrawable() != null) {
    iconSize=Math.max(getIconDrawable().getIntrinsicWidth(),getIconDrawable().getIntrinsicHeight());
  }
  int iconOffset=(getCircleSize() - (iconSize > 0 ? iconSize : mIconSize)) / 2;
  int circleInsetHorizontal=hasShadow() ? mShadowRadius + Math.abs(mShadowXOffset) : 0;
  int circleInsetVertical=hasShadow() ? mShadowRadius + Math.abs(mShadowYOffset) : 0;
  if (mProgressBarEnabled) {
    circleInsetHorizontal+=mProgressWidth;
    circleInsetVertical+=mProgressWidth;
  }
  layerDrawable.setLayerInset(hasShadow() ? 2 : 1,circleInsetHorizontal + iconOffset,circleInsetVertical + iconOffset,circleInsetHorizontal + iconOffset,circleInsetVertical + iconOffset);
  setBackgroundCompat(layerDrawable);
}
