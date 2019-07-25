public boolean expand(){
  if (menuState != MenuState.CLOSE) {
    return false;
  }
  setMenuState(MenuState.OPENING);
  View contentView=getContentView();
  int width=contentView.getMeasuredWidth();
  final int menuOffset=(int)((width * 0.8f) / 3f);
  container.addView(backgroundView,0,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
  ViewGroup.MarginLayoutParams pluginParams=new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
  MarginLayoutParamsCompat.setMarginStart(pluginParams,menuOffset);
  container.addView(pluginView,pluginParams);
  pluginView.setTranslationX(160.0f);
  container.addView(foregroundView,new ViewGroup.LayoutParams(menuOffset,ViewGroup.LayoutParams.MATCH_PARENT));
  final int offset=width - (width / 3);
  ViewCompat.animate(contentView).translationX(-offset).scaleX(0.8f).scaleY(0.8f).translationZ(10.f).setInterpolator(EXPAND_COLLAPSE_INTERPOLATOR).setListener(new ViewPropertyAnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    View view){
      setMenuState(MenuState.OPEN);
      pluginView.requestFocus();
    }
  }
).start();
  View overlayView=getOverlayView();
  if (overlayView != null) {
    ViewCompat.animate(overlayView).translationX(-offset).scaleX(0.8f).scaleY(0.8f).translationZ(10.f).setInterpolator(EXPAND_COLLAPSE_INTERPOLATOR).start();
  }
  ViewCompat.animate(pluginView).withLayer().alpha(1.0f).translationX(0.0f).setInterpolator(EXPAND_COLLAPSE_INTERPOLATOR).start();
  Activity activity=ActivityUtil.findActivity(container);
  if (activity != null) {
    Window window=activity.getWindow();
    Drawable decorBackground=cloneDrawable(window.getDecorView().getBackground());
    background=contentView.getBackground();
    if (decorBackground != null) {
      ViewCompat.setBackground(contentView,decorBackground);
    }
  }
  return true;
}
