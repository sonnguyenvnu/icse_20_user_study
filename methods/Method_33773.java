public void slidingToY(View view,float y){
  if (view == null) {
    return;
  }
  view.clearAnimation();
  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
    view.setY(y);
  }
 else {
    ViewHelper.setY(view,y);
  }
}
