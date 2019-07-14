@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  if (savedInstanceState == null) {
    issueState=(IssueState)getArguments().getSerializable(BundleConstant.EXTRA);
  }
  getPresenter().onSetPullType(getIssuesType());
  recycler.setEmptyView(stateLayout,refresh);
  stateLayout.setOnReloadListener(this);
  refresh.setOnRefreshListener(this);
  adapter=new PullRequestAdapter(getPresenter().getPullRequests(),false,true);
  adapter.setListener(getPresenter());
  getLoadMore().initialize(getPresenter().getCurrentPage(),getPresenter().getPreviousTotal());
  recycler.setAdapter(adapter);
  recycler.addDivider();
  recycler.addOnScrollListener(getLoadMore());
  if (savedInstanceState == null || (getPresenter().getPullRequests().isEmpty() && !getPresenter().isApiCalled())) {
    onRefresh();
  }
  stateLayout.setEmptyText(getString((R.string.no_pull_requests)));
  fastScroller.attachRecyclerView(recycler);
}
