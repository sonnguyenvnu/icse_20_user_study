/** 
 * Adjusts hypothetical future scale and translate values to keep scale within the allowed range and the image on screen. Minimum scale is set so one dimension fills the view and the image is centered on the other dimension. Used to calculate what the target of an animation should be.
 * @param center Whether the image should be centered in the dimension it's too small to fill. While animating this can be false to avoid changes in direction as bounds are reached.
 * @param sat    The scale we want and the translation we're aiming for. The values are adjusted to be valid.
 */
private void fitToBounds(boolean center,ScaleAndTranslate sat){
  if (panLimit == PAN_LIMIT_OUTSIDE && isReady()) {
    center=false;
  }
  PointF vTranslate=sat.vTranslate;
  float scale=limitedScale(sat.scale);
  float scaleWidth=scale * sWidth();
  float scaleHeight=scale * sHeight();
  if (panLimit == PAN_LIMIT_CENTER && isReady()) {
    vTranslate.x=Math.max(vTranslate.x,getWidth() / 2 - scaleWidth);
    vTranslate.y=Math.max(vTranslate.y,getHeight() / 2 - scaleHeight);
  }
 else   if (center) {
    vTranslate.x=Math.max(vTranslate.x,getWidth() - scaleWidth);
    vTranslate.y=Math.max(vTranslate.y,getHeight() - scaleHeight);
  }
 else {
    vTranslate.x=Math.max(vTranslate.x,-scaleWidth);
    vTranslate.y=Math.max(vTranslate.y,-scaleHeight);
  }
  float xPaddingRatio=getPaddingLeft() > 0 || getPaddingRight() > 0 ? getPaddingLeft() / (float)(getPaddingLeft() + getPaddingRight()) : 0.5f;
  float yPaddingRatio=getPaddingTop() > 0 || getPaddingBottom() > 0 ? getPaddingTop() / (float)(getPaddingTop() + getPaddingBottom()) : 0.5f;
  float maxTx;
  float maxTy;
  if (panLimit == PAN_LIMIT_CENTER && isReady()) {
    maxTx=Math.max(0,getWidth() / 2);
    maxTy=Math.max(0,getHeight() / 2);
  }
 else   if (center) {
    maxTx=Math.max(0,(getWidth() - scaleWidth) * xPaddingRatio);
    maxTy=Math.max(0,(getHeight() - scaleHeight) * yPaddingRatio);
  }
 else {
    maxTx=Math.max(0,getWidth());
    maxTy=Math.max(0,getHeight());
  }
  vTranslate.x=Math.min(vTranslate.x,maxTx);
  vTranslate.y=Math.min(vTranslate.y,maxTy);
  sat.scale=scale;
}
