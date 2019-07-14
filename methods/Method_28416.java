@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  stateLayout.setEmptyText(R.string.no_search_results);
  getLoadMore().initialize(getPresenter().getCurrentPage(),getPresenter().getPreviousTotal());
  stateLayout.setOnReloadListener(this);
  refresh.setOnRefreshListener(this);
  recycler.setEmptyView(stateLayout,refresh);
  adapter=new UsersAdapter(getPresenter().getUsers());
  adapter.setListener(getPresenter());
  recycler.setAdapter(adapter);
  recycler.addKeyLineDivider();
  if (savedInstanceState != null) {
    if (!InputHelper.isEmpty(searchQuery) && getPresenter().getUsers().isEmpty() && !getPresenter().isApiCalled()) {
      onRefresh();
    }
  }
  if (InputHelper.isEmpty(searchQuery)) {
    stateLayout.showEmptyState();
  }
  fastScroller.attachRecyclerView(recycler);
}
