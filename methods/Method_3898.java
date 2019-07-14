@NonNull private OrientationHelper getHorizontalHelper(@NonNull RecyclerView.LayoutManager layoutManager){
  if (mHorizontalHelper == null || mHorizontalHelper.mLayoutManager != layoutManager) {
    mHorizontalHelper=OrientationHelper.createHorizontalHelper(layoutManager);
  }
  return mHorizontalHelper;
}
