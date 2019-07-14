@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  if (getIssue() == null) {
    throw new NullPointerException("Issue went missing!!!");
  }
  getPresenter().setCommentId(getCommentId());
  if (issueCallback != null && issueCallback.getData() != null) {
    adapter=new IssuesTimelineAdapter(getPresenter().getEvents(),this,true,this,issueCallback.getData().getLogin(),issueCallback.getData().getUser().getLogin());
  }
 else {
    adapter=new IssuesTimelineAdapter(getPresenter().getEvents(),this,true,this,"","");
  }
  recycler.setVerticalScrollBarEnabled(false);
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
    onSetHeader(TimelineModel.constructHeader(getIssue()));
    onRefresh();
  }
 else   if (getPresenter().getEvents().isEmpty() || getPresenter().getEvents().size() == 1) {
    onRefresh();
  }
}
