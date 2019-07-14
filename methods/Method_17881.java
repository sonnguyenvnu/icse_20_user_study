private void mountView(View view,int flags){
  view.setDuplicateParentStateEnabled(MountItem.isDuplicateParentState(flags));
  mIsChildDrawingOrderDirty=true;
  if (view instanceof ComponentHost && view.getParent() == this) {
    finishTemporaryDetach(view);
    view.setVisibility(VISIBLE);
    return;
  }
  LayoutParams lp=view.getLayoutParams();
  if (lp == null) {
    lp=generateDefaultLayoutParams();
    view.setLayoutParams(lp);
  }
  if (mInLayout) {
    super.addViewInLayout(view,-1,view.getLayoutParams(),true);
  }
 else {
    super.addView(view,-1,view.getLayoutParams());
  }
}
