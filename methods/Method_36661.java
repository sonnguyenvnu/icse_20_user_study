/** 
 * Bind a recyclerView to Tangram. After calling this,  {@link GroupBasicAdapter},  {@link VirtualLayoutManager} are auto binded.
 * @param view A plain recyclerView with no adapter or layoutManager binded.
 */
public void bindView(@NonNull final RecyclerView view){
  Preconditions.checkArgument(view != null,"view must not be null");
  if (mContentView != null) {
    mContentView.setAdapter(null);
    mContentView.setLayoutManager(null);
  }
  this.mContentView=view;
  this.mContentView.setLayoutManager(mLayoutManager);
  mLayoutManager.setPerformanceMonitor(mPerformanceMonitor);
  if (mGroupBasicAdapter == null) {
    this.mGroupBasicAdapter=mAdapterBuilder.newAdapter(mContext,mLayoutManager,this);
    mGroupBasicAdapter.setPerformanceMonitor(mPerformanceMonitor);
    mGroupBasicAdapter.setErrorSupport(getService(InternalErrorSupport.class));
  }
  if (mContentView.getRecycledViewPool() != null) {
    mContentView.setRecycledViewPool(new InnerRecycledViewPool(mContentView.getRecycledViewPool()));
  }
  register(GroupBasicAdapter.class,mGroupBasicAdapter);
  register(RecyclerView.RecycledViewPool.class,mContentView.getRecycledViewPool());
  this.mContentView.setAdapter(mGroupBasicAdapter);
  if (VERSION.SDK_INT < 21) {
    this.mContentView.setChildDrawingOrderCallback(new DrawingOrderCallback());
  }
}
