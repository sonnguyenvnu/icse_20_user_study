@Override public final void executePropertyChanges(@NonNull ViewGroup container,@Nullable View from,@Nullable View to,@Nullable Transition transition,boolean isPush){
  if (to != null && removedViews.size() > 0) {
    to.setVisibility(View.VISIBLE);
    for (    ViewParentPair removedView : removedViews) {
      removedView.parent.addView(removedView.view);
    }
    removedViews.clear();
  }
  super.executePropertyChanges(container,from,to,transition,isPush);
}
