@Override public void onHandleComment(@NonNull String text,@Nullable Bundle bundle,String gistId){
  CommentRequestModel model=new CommentRequestModel();
  model.setBody(text);
  manageDisposable(RxHelper.getObservable(RestProvider.getGistService(isEnterprise()).createGistComment(gistId,model)).doOnSubscribe(disposable -> sendToView(view -> view.showBlockingProgress(0))).subscribe(comment -> sendToView(view -> view.onAddNewComment(comment)),throwable -> {
    onError(throwable);
    sendToView(GistCommentsMvp.View::hideBlockingProgress);
  }
));
}
