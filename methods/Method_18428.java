private static void mountViewIncrementally(View view,boolean processVisibilityOutputs){
  assertMainThread();
  if (view instanceof LithoView) {
    final LithoView lithoView=(LithoView)view;
    if (lithoView.isIncrementalMountEnabled()) {
      if (!processVisibilityOutputs) {
        lithoView.performIncrementalMount(new Rect(0,0,view.getWidth(),view.getHeight()),false);
      }
 else {
        lithoView.performIncrementalMount();
      }
    }
  }
 else   if (view instanceof ViewGroup) {
    final ViewGroup viewGroup=(ViewGroup)view;
    for (int i=0; i < viewGroup.getChildCount(); i++) {
      final View childView=viewGroup.getChildAt(i);
      mountViewIncrementally(childView,processVisibilityOutputs);
    }
  }
}
