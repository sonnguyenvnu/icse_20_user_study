@Override public void onCheckAppVersion(){
  makeRestCall(RestProvider.getRepoService(false).getLatestRelease("k0shk0sh","FastHub"),release -> {
    if (release != null) {
      if (!BuildConfig.VERSION_NAME.contains(release.getTagName())) {
        sendToView(CreateIssueMvp.View::onShowUpdate);
      }
 else {
        sendToView(BaseMvp.FAView::hideProgress);
      }
    }
  }
,false);
}
