@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  if (getArguments() == null) {
    throw new NullPointerException("Bundle is null, username is required");
  }
  stateLayout.setEmptyText(R.string.no_followers);
  stateLayout.setOnReloadListener(this);
  refresh.setOnRefreshListener(this);
  recycler.setEmptyView(stateLayout,refresh);
  getLoadMore().initialize(getPresenter().getCurrentPage(),getPresenter().getPreviousTotal());
  adapter=new UsersAdapter(getPresenter().getFollowers());
  adapter.setListener(getPresenter());
  recycler.setAdapter(adapter);
  recycler.addOnScrollListener(getLoadMore());
  recycler.addKeyLineDivider();
  if (getPresenter().getFollowers().isEmpty() && !getPresenter().isApiCalled()) {
    onRefresh();
  }
  fastScroller.attachRecyclerView(recycler);
}
