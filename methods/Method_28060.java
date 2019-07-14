@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  if (savedInstanceState == null) {
    callApi();
  }
  refresh.setOnRefreshListener(this::callApi);
  stateLayout.setOnReloadListener(v -> callApi());
  boolean isAssinees=getArguments().getBoolean(BundleConstant.EXTRA_TWO);
  stateLayout.setEmptyText(isAssinees ? R.string.no_assignees : R.string.no_reviewers);
  recycler.setEmptyView(stateLayout,refresh);
  recycler.addKeyLineDivider();
  title.setText(isAssinees ? R.string.assignees : R.string.reviewers);
  adapter=new AssigneesAdapter(getPresenter().getList(),this);
  recycler.setAdapter(adapter);
  fastScroller.attachRecyclerView(recycler);
}
