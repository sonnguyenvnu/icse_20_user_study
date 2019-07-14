@Override public void onHandleComment(@NonNull String text,@Nullable Bundle bundle){
  CommentRequestModel model=new CommentRequestModel();
  model.setBody(text);
  manageDisposable(RxHelper.getObservable(RestProvider.getRepoService(isEnterprise()).postCommitComment(login,repoId,sha,model)).doOnSubscribe(disposable -> sendToView(view -> view.showBlockingProgress(0))).subscribe(comment -> sendToView(view -> view.addComment(comment)),throwable -> {
    onError(throwable);
    sendToView(CommitCommentsMvp.View::hideBlockingProgress);
  }
));
}
