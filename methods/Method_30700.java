public static void inject(Activity activity){
  ViewGroup contentLayout=findContentLayout(activity);
  ScalpelFrameLayout scalpelLayout=new ScalpelFrameLayout(activity);
  while (contentLayout.getChildCount() > 0) {
    View view=contentLayout.getChildAt(0);
    contentLayout.removeViewAt(0);
    scalpelLayout.addView(view);
  }
  contentLayout.addView(scalpelLayout);
}
