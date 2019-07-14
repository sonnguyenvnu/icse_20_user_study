public void slidingByDelta(View view,float delta){
  if (view == null) {
    return;
  }
  view.clearAnimation();
  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
    view.setTranslationY(delta);
  }
 else {
    ViewHelper.setTranslationY(view,delta);
  }
}
