@Override public void onCommentAdded(@NonNull String comment,@NonNull CommitLinesModel item,Bundle bundle){
  getPresenter().onSubmitComment(comment,item,bundle);
}
