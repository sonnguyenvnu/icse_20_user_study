private void unStarRepo(@Nullable String id,@Nullable String login,boolean isEnterprise){
  if (id != null && login != null) {
    String msg=getString(R.string.un_starring,id);
    RestProvider.getRepoService(isEnterprise).unstarRepo(login,id).doOnSubscribe(disposable -> showNotification(msg)).subscribeOn(Schedulers.io()).subscribe(response -> {
    }
,throwable -> hideNotification(msg),() -> hideNotification(msg));
  }
}
