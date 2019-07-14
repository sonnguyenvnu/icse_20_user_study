@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  adapter=new NotificationsAdapter(getPresenter().getNotifications(),true,true);
  adapter.setListener(getPresenter());
  refresh.setOnRefreshListener(this);
  stateLayout.setEmptyText(R.string.no_notifications);
  stateLayout.setOnReloadListener(v -> onRefresh());
  recycler.setEmptyView(stateLayout,refresh);
  recycler.setAdapter(adapter);
  recycler.addDivider(NotificationsViewHolder.class);
  if (savedInstanceState == null || !getPresenter().isApiCalled()) {
    onRefresh();
  }
  fastScroller.attachRecyclerView(recycler);
}
