@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  adapter=new PullRequestAdapter(getPresenter().getPinnedPullRequest(),true,true);
  adapter.setListener(getPresenter());
  stateLayout.setEmptyText(getString((R.string.no_pull_requests)));
  recycler.setEmptyView(stateLayout,refresh);
  recycler.setAdapter(adapter);
  recycler.addKeyLineDivider();
  refresh.setOnRefreshListener(() -> getPresenter().onReload());
  stateLayout.setOnReloadListener(v -> getPresenter().onReload());
  if (savedInstanceState == null) {
    stateLayout.showProgress();
  }
  fastScroller.attachRecyclerView(recycler);
}
