@Override public void onInitDataAndRequest(@NonNull String login,@NonNull String repoId,@NonNull String path,@NonNull String ref,boolean clear,@Nullable RepoFile toAppend){
  if (clear)   pathsModel.clear();
  this.login=login;
  this.repoId=repoId;
  this.ref=ref;
  this.path=path;
  List<RepoFile> cachedFiles=getCachedFiles(path,ref);
  if (cachedFiles != null && !cachedFiles.isEmpty()) {
    files.clear();
    files.addAll(cachedFiles);
    sendToView(view -> {
      view.onNotifyAdapter();
      view.onUpdateTab(toAppend);
    }
);
  }
 else {
    onCallApi(toAppend);
  }
}
