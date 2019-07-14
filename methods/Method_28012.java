@Override public void onSetData(@NonNull String login,@NonNull String repoId,@NonNull String path,@NonNull String ref,boolean clear,@Nullable RepoFile toAppend){
  getPresenter().onInitDataAndRequest(login,repoId,path,ref,clear,toAppend);
}
