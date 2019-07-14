@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  gistId=getArguments().getString("gistId");
  recycler.setEmptyView(stateLayout,refresh);
  if (gistId == null)   return;
  stateLayout.setEmptyText(R.string.no_comments);
  recycler.setItemViewCacheSize(30);
  refresh.setOnRefreshListener(this);
  stateLayout.setOnReloadListener(this);
  adapter=new CommentsAdapter(getPresenter().getComments());
  adapter.setListener(getPresenter());
  getLoadMore().initialize(getPresenter().getCurrentPage(),getPresenter().getPreviousTotal());
  recycler.setAdapter(adapter);
  recycler.addKeyLineDivider();
  recycler.addOnScrollListener(getLoadMore());
  recycler.addNormalSpacingDivider();
  if (getPresenter().getComments().isEmpty() && !getPresenter().isApiCalled()) {
    sparseBooleanArray=new SparseBooleanArrayParcelable();
    onRefresh();
  }
  fastScroller.attachRecyclerView(recycler);
}
