private static ScalpelFrameLayout findScalpelLayout(Activity activity){
  return (ScalpelFrameLayout)findContentLayout(activity).getChildAt(0);
}
