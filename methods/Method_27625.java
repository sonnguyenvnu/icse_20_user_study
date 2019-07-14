@Override public void onWorkOffline(@NonNull String gistId){
  if (comments.isEmpty()) {
    manageDisposable(RxHelper.getObservable(Comment.getGistComments(gistId).toObservable()).subscribe(localComments -> sendToView(view -> view.onNotifyAdapter(localComments,1))));
  }
 else {
    sendToView(BaseMvp.FAView::hideProgress);
  }
}
