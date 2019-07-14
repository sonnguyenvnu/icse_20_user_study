@SuppressLint("NewApi") public static void clearDrawableAnimation(View view){
  if (Build.VERSION.SDK_INT < 21 || view == null) {
    return;
  }
  Drawable drawable;
  if (view instanceof ListView) {
    drawable=((ListView)view).getSelector();
    if (drawable != null) {
      drawable.setState(StateSet.NOTHING);
    }
  }
 else {
    drawable=view.getBackground();
    if (drawable != null) {
      drawable.setState(StateSet.NOTHING);
      drawable.jumpToCurrentState();
    }
  }
}
