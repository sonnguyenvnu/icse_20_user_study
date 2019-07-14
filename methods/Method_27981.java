private void getCommitCount(@NonNull String branch){
  manageDisposable(RxHelper.safeObservable(RxHelper.getObservable(RestProvider.getRepoService(isEnterprise()).getCommitCounts(login,repoId,branch))).subscribe(response -> {
    if (response != null) {
      sendToView(view -> view.onShowCommitCount(response.getLast()));
    }
  }
,Throwable::printStackTrace));
}
