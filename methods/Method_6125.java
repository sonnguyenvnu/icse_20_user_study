public static void setViewPagerEdgeEffectColor(ViewPager viewPager,int color){
  if (Build.VERSION.SDK_INT >= 21) {
    try {
      Field field=ViewPager.class.getDeclaredField("mLeftEdge");
      field.setAccessible(true);
      EdgeEffect mLeftEdge=(EdgeEffect)field.get(viewPager);
      if (mLeftEdge != null) {
        mLeftEdge.setColor(color);
      }
      field=ViewPager.class.getDeclaredField("mRightEdge");
      field.setAccessible(true);
      EdgeEffect mRightEdge=(EdgeEffect)field.get(viewPager);
      if (mRightEdge != null) {
        mRightEdge.setColor(color);
      }
    }
 catch (    Exception ignore) {
    }
  }
}
