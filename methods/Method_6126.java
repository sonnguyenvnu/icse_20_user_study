public static void setScrollViewEdgeEffectColor(HorizontalScrollView scrollView,int color){
  if (Build.VERSION.SDK_INT >= 21) {
    try {
      Field field=HorizontalScrollView.class.getDeclaredField("mEdgeGlowLeft");
      field.setAccessible(true);
      EdgeEffect mEdgeGlowTop=(EdgeEffect)field.get(scrollView);
      if (mEdgeGlowTop != null) {
        mEdgeGlowTop.setColor(color);
      }
      field=HorizontalScrollView.class.getDeclaredField("mEdgeGlowRight");
      field.setAccessible(true);
      EdgeEffect mEdgeGlowBottom=(EdgeEffect)field.get(scrollView);
      if (mEdgeGlowBottom != null) {
        mEdgeGlowBottom.setColor(color);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
}
