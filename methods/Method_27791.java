@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  adapter=new IssuesAdapter(getPresenter().getPinnedIssue(),true,true,true);
  adapter.setListener(getPresenter());
  stateLayout.setEmptyText(R.string.no_issues);
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
