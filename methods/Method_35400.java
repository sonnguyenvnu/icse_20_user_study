@Override public void onFoldersAdded(List<Folder> allNewFolders){
  int newItemCount=allNewFolders.size() - (mAdapter.getData() == null ? 0 : mAdapter.getData().size());
  mAdapter.setData(allNewFolders);
  mAdapter.notifyDataSetChanged();
  mAdapter.updateFooterView();
  if (newItemCount > 0) {
    String toast=getResources().getQuantityString(R.plurals.mp_folders_created_formatter,newItemCount,newItemCount);
    Toast.makeText(getActivity(),toast,Toast.LENGTH_SHORT).show();
  }
}
