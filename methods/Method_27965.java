@Override public void onFragmentCreated(@Nullable Bundle bundle){
  if (sha == null) {
    if (bundle != null) {
      sha=bundle.getString(BundleConstant.ID);
    }
  }
  if (!InputHelper.isEmpty(sha)) {
    CommitFileListModel commitFiles=CommitFilesSingleton.getInstance().getByCommitId(sha);
    if (commitFiles != null) {
      manageObservable(Observable.just(commitFiles).map(CommitFileChanges::construct).doOnSubscribe(disposable -> sendToView(CommitFilesMvp.View::clearAdapter)).doOnNext(commitFileChanges -> {
        sendToView(view -> view.onNotifyAdapter(commitFileChanges));
      }
).doOnComplete(() -> sendToView(BaseMvp.FAView::hideProgress)));
    }
  }
 else {
    throw new NullPointerException("Bundle is null");
  }
}
