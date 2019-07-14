@Override public void onDeleteFile(@NonNull String message,@NonNull RepoFile item,@NonNull String branch){
  CommitRequestModel body=new CommitRequestModel(message,null,item.getSha(),branch);
  makeRestCall(RestProvider.getContentService(isEnterprise()).deleteFile(login,repoId,item.getPath(),ref,body),gitCommitModel -> sendToView(SwipeRefreshLayout.OnRefreshListener::onRefresh));
}
