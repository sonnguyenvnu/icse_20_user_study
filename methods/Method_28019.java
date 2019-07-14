private boolean canOpen(@NonNull RepoFile item){
  return item.getDownloadUrl() != null && !MarkDownProvider.isImage(item.getDownloadUrl()) && !MarkDownProvider.isArchive(item.getDownloadUrl());
}
