@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  stateLayout.setEmptyText(R.string.no_search_results);
  getLoadMore().initialize(getPresenter().getCurrentPage(),getPresenter().getPreviousTotal());
  stateLayout.setOnReloadListener(this);
  refresh.setOnRefreshListener(this);
  recycler.setEmptyView(stateLayout,refresh);
  adapter=new SearchCodeAdapter(getPresenter().getCodes());
  adapter.showRepoName(showRepoName);
  adapter.setListener(getPresenter());
  recycler.setAdapter(adapter);
  recycler.addDivider();
  if (!InputHelper.isEmpty(searchQuery) && getPresenter().getCodes().isEmpty() && !getPresenter().isApiCalled()) {
    onRefresh();
  }
  if (InputHelper.isEmpty(searchQuery)) {
    stateLayout.showEmptyState();
  }
  fastScroller.attachRecyclerView(recycler);
}
