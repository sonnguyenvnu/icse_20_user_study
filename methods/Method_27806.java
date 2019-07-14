@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  adapter=new PinnedReposAdapter(getPresenter().getPinnedRepos(),getPresenter());
  stateLayout.setEmptyText(R.string.empty_pinned_repos);
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
