private void unWatchRepo(@Nullable String id,@Nullable String login,boolean isEnterprise){
  if (id != null && login != null) {
    String msg=getString(R.string.un_watching,id);
    RestProvider.getRepoService(isEnterprise).unwatchRepo(login,id).doOnSubscribe(disposable -> showNotification(msg)).subscribeOn(Schedulers.io()).subscribe(response -> {
    }
,throwable -> hideNotification(msg),() -> hideNotification(msg));
  }
}
