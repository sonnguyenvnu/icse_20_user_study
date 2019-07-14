private void fillAreaView(RectF targetRect,boolean allowZoomOut){
  final float[] currentScale=new float[]{1.0f};
  float scale=Math.max(targetRect.width() / areaView.getCropWidth(),targetRect.height() / areaView.getCropHeight());
  float newScale=state.getScale() * scale;
  boolean ensureFit=false;
  if (newScale > MAX_SCALE) {
    scale=MAX_SCALE / state.getScale();
    ensureFit=true;
  }
  float statusBarHeight=(Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0);
  final float x=(targetRect.centerX() - imageView.getWidth() / 2) / areaView.getCropWidth() * state.getOrientedWidth();
  final float y=(targetRect.centerY() - (imageView.getHeight() - bottomPadding + statusBarHeight) / 2) / areaView.getCropHeight() * state.getOrientedHeight();
  final float targetScale=scale;
  final boolean animEnsureFit=ensureFit;
  ValueAnimator animator=ValueAnimator.ofFloat(0.0f,1.0f);
  animator.addUpdateListener(animation -> {
    float value=(Float)animation.getAnimatedValue();
    float deltaScale=(1.0f + ((targetScale - 1.0f) * value)) / currentScale[0];
    currentScale[0]*=deltaScale;
    state.scale(deltaScale,x,y);
    updateMatrix();
  }
);
  animator.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      if (animEnsureFit)       fitContentInBounds(false,false,true);
    }
  }
);
  areaView.fill(targetRect,animator,true);
  initialAreaRect.set(targetRect);
}
