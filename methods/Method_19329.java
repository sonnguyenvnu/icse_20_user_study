private void enableStickyHeader(RecyclerView recyclerView){
  if (mIsCircular) {
    Log.w(TAG,"Sticky header is not supported for circular RecyclerViews");
    return;
  }
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
    Log.w(TAG,"Sticky header is supported only on ICS (API14) and above");
    return;
  }
  if (recyclerView == null) {
    return;
  }
  SectionsRecyclerView sectionsRecycler=SectionsRecyclerView.getParentRecycler(recyclerView);
  if (sectionsRecycler == null) {
    return;
  }
  if (mStickyHeaderControllerFactory == null) {
    mStickyHeaderController=new StickyHeaderControllerImpl((HasStickyHeader)this);
  }
 else {
    mStickyHeaderController=mStickyHeaderControllerFactory.getController((HasStickyHeader)this);
  }
  mStickyHeaderController.init(sectionsRecycler);
}
