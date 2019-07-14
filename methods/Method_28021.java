@Override public void onWorkOffline(){
  if ((repoId == null || login == null) || !files.isEmpty())   return;
  manageDisposable(RxHelper.getObservable(RepoFile.getFiles(login,repoId).toObservable()).flatMap(response -> {
    if (response != null) {
      return Observable.fromIterable(response).filter(repoFile -> repoFile != null && repoFile.getType() != null).sorted((repoFile,repoFile2) -> repoFile2.getType().compareTo(repoFile.getType()));
    }
    return Observable.empty();
  }
).toList().subscribe(models -> {
    files.addAll(models);
    sendToView(RepoFilesMvp.View::onNotifyAdapter);
  }
));
}
