@OnUnmount static void onUnmount(ComponentContext context,SectionsRecyclerView sectionsRecycler,@Prop Binder<RecyclerView> binder,@Prop(optional=true) RecyclerView.ItemDecoration itemDecoration,@Prop(optional=true,resType=ResType.COLOR) @Nullable Integer refreshProgressBarBackgroundColor,@Prop(optional=true) SnapHelper snapHelper){
  final RecyclerView recyclerView=sectionsRecycler.getRecyclerView();
  if (recyclerView == null) {
    throw new IllegalStateException("RecyclerView not found, it should not be removed from SwipeRefreshLayout " + "before unmounting");
  }
  recyclerView.setId(RecyclerSpec.recyclerViewId);
  if (refreshProgressBarBackgroundColor != null) {
    sectionsRecycler.setProgressBackgroundColorSchemeColor(DEFAULT_REFRESH_SPINNER_BACKGROUND_COLOR);
  }
  if (itemDecoration != null) {
    recyclerView.removeItemDecoration(itemDecoration);
  }
  binder.unmount(recyclerView);
  if (snapHelper != null) {
    snapHelper.attachToRecyclerView(null);
  }
}
