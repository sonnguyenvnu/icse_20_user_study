private void forkRepo(@Nullable String id,@Nullable String login,boolean isEnterprise){
  if (id != null && login != null) {
    String msg=getString(R.string.forking,id);
    RestProvider.getRepoService(isEnterprise).forkRepo(login,id).doOnSubscribe(disposable -> showNotification(msg)).subscribeOn(Schedulers.io()).subscribe(response -> {
    }
,throwable -> hideNotification(msg),() -> hideNotification(msg));
  }
}
