public void setBackgroundImage(Drawable bitmap,boolean motion){
  backgroundDrawable=bitmap;
  if (motion) {
    if (parallaxEffect == null) {
      parallaxEffect=new WallpaperParallaxEffect(getContext());
      parallaxEffect.setCallback((offsetX,offsetY) -> {
        translationX=offsetX;
        translationY=offsetY;
        invalidate();
      }
);
      if (getMeasuredWidth() != 0 && getMeasuredHeight() != 0) {
        parallaxScale=parallaxEffect.getScale(getMeasuredWidth(),getMeasuredHeight());
      }
    }
    if (!paused) {
      parallaxEffect.setEnabled(true);
    }
  }
 else   if (parallaxEffect != null) {
    parallaxEffect.setEnabled(false);
    parallaxEffect=null;
    parallaxScale=1.0f;
    translationX=0;
    translationY=0;
  }
  invalidate();
}
