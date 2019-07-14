@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  stateLayout.setEmptyText(R.string.no_files);
  stateLayout.showEmptyState();
  recycler.setEmptyView(stateLayout,refresh);
  refresh.setEnabled(false);
  adapter=new GistFilesAdapter(getPresenter().getFiles(),getPresenter(),isOwner);
  recycler.setAdapter(adapter);
  if (getArguments() != null && savedInstanceState == null) {
    ArrayList<FilesListModel> filesListModel=getArguments().getParcelableArrayList(BundleConstant.ITEM);
    isOwner=getArguments().getBoolean(BundleConstant.EXTRA_TYPE);
    onInitFiles(filesListModel,isOwner);
    setArguments(null);
  }
 else {
    onInitFiles(getPresenter().getFiles(),isOwner);
  }
  fastScroller.attachRecyclerView(recycler);
}
