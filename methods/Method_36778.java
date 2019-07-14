private View findCanScrollView(View v){
  if (v instanceof ViewGroup) {
    ViewGroup target=(ViewGroup)v;
    if ((target instanceof UltraViewPager || target instanceof RecyclerView) && target.getVisibility() == View.VISIBLE) {
      return target;
    }
 else {
      for (int i=0; i < target.getChildCount(); ++i) {
        View view=findCanScrollView(target.getChildAt(i));
        if (view != null) {
          return view;
        }
      }
      return null;
    }
  }
 else {
    return null;
  }
}
