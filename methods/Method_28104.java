@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  if (getArguments() == null) {
    throw new NullPointerException("Bundle is null, username is required");
  }
switch (getPresenter().getType()) {
case RepoMiscMVp.FORKS:
    toolbar.setTitle(R.string.forks);
  stateLayout.setEmptyText(String.format("%s %s",getString(R.string.no),getString(R.string.forks)));
break;
case RepoMiscMVp.STARS:
toolbar.setTitle(R.string.stars);
stateLayout.setEmptyText(String.format("%s %s",getString(R.string.no),getString(R.string.stars)));
break;
case RepoMiscMVp.WATCHERS:
toolbar.setTitle(R.string.watchers);
stateLayout.setEmptyText(String.format("%s %s",getString(R.string.no),getString(R.string.watchers)));
break;
}
toolbar.setNavigationIcon(R.drawable.ic_clear);
toolbar.setNavigationOnClickListener(v -> dismiss());
stateLayout.setOnReloadListener(v -> getPresenter().onCallApi(1,null));
refresh.setOnRefreshListener(() -> getPresenter().onCallApi(1,null));
recycler.setEmptyView(stateLayout,refresh);
getLoadMore().initialize(getPresenter().getCurrentPage(),getPresenter().getPreviousTotal());
adapter=new UsersAdapter(getPresenter().getList());
adapter.setListener(getPresenter());
recycler.setAdapter(adapter);
recycler.addOnScrollListener(getLoadMore());
recycler.addKeyLineDivider();
if (getPresenter().getList().isEmpty() && !getPresenter().isApiCalled()) {
getPresenter().onCallApi(1,null);
}
fastScroller.attachRecyclerView(recycler);
}
