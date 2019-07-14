@Override public void onOpenFile(@NonNull FilesListModel item,int position){
  if (canOpen(item) && !isOwner) {
    CodeViewerActivity.startActivity(getContext(),item.getRawUrl(),item.getRawUrl());
  }
 else   if (isOwner && canOpen(item)) {
    onEditFile(item,position);
  }
}
