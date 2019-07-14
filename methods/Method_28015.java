@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  stateLayout.setEmptyText(R.string.no_files);
  refresh.setOnRefreshListener(this);
  stateLayout.setOnReloadListener(v -> onRefresh());
  recycler.setEmptyView(stateLayout,refresh);
  adapter=new RepoFilesAdapter(getPresenter().getFiles());
  adapter.setListener(getPresenter());
  recycler.setAdapter(adapter);
  fastScroller.attachRecyclerView(recycler);
}
