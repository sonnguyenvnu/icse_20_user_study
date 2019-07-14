@OnUnbind static void onUnbind(ComponentContext context,SectionsRecyclerView sectionsRecycler,@Prop Binder<RecyclerView> binder,@Prop(optional=true) RecyclerEventsController recyclerEventsController,@Prop(optional=true,varArg="onScrollListener") List<OnScrollListener> onScrollListeners,@FromBind ItemAnimator oldAnimator){
  final LithoRecylerView recyclerView=(LithoRecylerView)sectionsRecycler.getRecyclerView();
  if (recyclerView == null) {
    throw new IllegalStateException("RecyclerView not found, it should not be removed from SwipeRefreshLayout " + "before unmounting");
  }
  recyclerView.setItemAnimator(oldAnimator);
  binder.unbind(recyclerView);
  if (recyclerEventsController != null) {
    recyclerEventsController.setSectionsRecyclerView(null);
  }
  if (onScrollListeners != null) {
    for (    OnScrollListener onScrollListener : onScrollListeners) {
      recyclerView.removeOnScrollListener(onScrollListener);
    }
  }
  recyclerView.setTouchInterceptor(null);
  sectionsRecycler.setOnRefreshListener(null);
}
