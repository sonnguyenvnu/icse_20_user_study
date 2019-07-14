@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  if (getPullRequest() == null) {
    throw new NullPointerException("PullRequest went missing!!!");
  }
  boolean isMerged=getPresenter().isMerged(getPullRequest());
  adapter=new IssuesTimelineAdapter(getPresenter().getEvents(),this,true,this,isMerged,getPresenter(),getPullRequest().getLogin(),getPullRequest().getUser().getLogin());
  stateLayout.setEmptyText(R.string.no_events);
  recycler.setEmptyView(stateLayout,refresh);
  refresh.setOnRefreshListener(this);
  stateLayout.setOnReloadListener(this);
  adapter.setListener(getPresenter());
  recycler.setAdapter(adapter);
  fastScroller.setVisibility(View.VISIBLE);
  fastScroller.attachRecyclerView(recycler);
  recycler.addDivider(TimelineCommentsViewHolder.class);
  getLoadMore().initialize(getPresenter().getCurrentPage(),getPresenter().getPreviousTotal());
  recycler.addOnScrollListener(getLoadMore());
  if (savedInstanceState == null) {
    onSetHeader(new TimelineModel(getPullRequest()));
    onRefresh();
  }
 else   if (getPresenter().getEvents().isEmpty() || getPresenter().getEvents().size() == 1) {
    onRefresh();
  }
}
