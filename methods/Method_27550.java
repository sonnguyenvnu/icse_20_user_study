@Override public void onUploaded(@Nullable String title,@Nullable String link){
  hideProgress();
  if (callback != null) {
    callback.onAppendLink(title,link != null ? link.replace("http:","https:") : null,isLink());
  }
  dismiss();
}
