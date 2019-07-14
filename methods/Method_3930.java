/** 
 * Set the  {@link LayoutManager} that this RecyclerView will use.<p>In contrast to other adapter-backed views such as  {@link android.widget.ListView}or  {@link android.widget.GridView}, RecyclerView allows client code to provide custom layout arrangements for child views. These arrangements are controlled by the {@link LayoutManager}. A LayoutManager must be provided for RecyclerView to function.</p> <p>Several default strategies are provided for common uses such as lists and grids.</p>
 * @param layout LayoutManager to use
 */
public void setLayoutManager(@Nullable LayoutManager layout){
  if (layout == mLayout) {
    return;
  }
  stopScroll();
  if (mLayout != null) {
    if (mItemAnimator != null) {
      mItemAnimator.endAnimations();
    }
    mLayout.removeAndRecycleAllViews(mRecycler);
    mLayout.removeAndRecycleScrapInt(mRecycler);
    mRecycler.clear();
    if (mIsAttached) {
      mLayout.dispatchDetachedFromWindow(this,mRecycler);
    }
    mLayout.setRecyclerView(null);
    mLayout=null;
  }
 else {
    mRecycler.clear();
  }
  mChildHelper.removeAllViewsUnfiltered();
  mLayout=layout;
  if (layout != null) {
    if (layout.mRecyclerView != null) {
      throw new IllegalArgumentException("LayoutManager " + layout + " is already attached to a RecyclerView:" + layout.mRecyclerView.exceptionLabel());
    }
    mLayout.setRecyclerView(this);
    if (mIsAttached) {
      mLayout.dispatchAttachedToWindow(this);
    }
  }
  mRecycler.updateViewCacheSize();
  requestLayout();
}
