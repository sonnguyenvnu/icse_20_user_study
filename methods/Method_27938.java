@Override public void onWorkOffline(){
  if (comments.isEmpty()) {
    manageDisposable(RxHelper.getObservable(Comment.getCommitComments(repoId(),login(),sha).toObservable()).flatMap(TimelineModel::construct).subscribe(models -> sendToView(view -> view.onNotifyAdapter(models,1))));
  }
 else {
    sendToView(CommitCommentsMvp.View::hideProgress);
  }
}
