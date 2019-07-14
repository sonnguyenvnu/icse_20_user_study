@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  if (savedInstanceState == null) {
    stateLayout.hideProgress();
  }
  stateLayout.setEmptyText(R.string.no_search_results);
  getLoadMore().initialize(getPresenter().getCurrentPage(),getPresenter().getPreviousTotal());
  stateLayout.setOnReloadListener(this);
  refresh.setOnRefreshListener(this);
  recycler.setEmptyView(stateLayout,refresh);
  adapter=new ReposAdapter(getPresenter().getRepos(),true,true);
  adapter.setListener(getPresenter());
  recycler.setAdapter(adapter);
  recycler.addKeyLineDivider();
  if (!InputHelper.isEmpty(searchQuery) && getPresenter().getRepos().isEmpty() && !getPresenter().isApiCalled()) {
    onRefresh();
  }
  if (InputHelper.isEmpty(searchQuery)) {
    stateLayout.showEmptyState();
  }
  fastScroller.attachRecyclerView(recycler);
}
