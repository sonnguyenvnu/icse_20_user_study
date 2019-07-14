@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  stateLayout.setEmptyText(R.string.no_search_results);
  recycler.setEmptyView(stateLayout,refresh);
  stateLayout.setOnReloadListener(this);
  refresh.setOnRefreshListener(this);
  adapter=new IssuesAdapter(getPresenter().getIssues(),true,false,true);
  adapter.setListener(getPresenter());
  getLoadMore().initialize(getPresenter().getCurrentPage(),getPresenter().getPreviousTotal());
  recycler.setAdapter(adapter);
  recycler.addOnScrollListener(getLoadMore());
  recycler.addKeyLineDivider();
  if (savedInstanceState != null) {
    if (!InputHelper.isEmpty(query) && getPresenter().getIssues().isEmpty()) {
      onRefresh();
    }
  }
  fastScroller.attachRecyclerView(recycler);
}
