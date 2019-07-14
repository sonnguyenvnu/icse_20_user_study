@Override public void onFragmentCreated(@NonNull Bundle bundle){
  repoId=bundle.getString(BundleConstant.ID);
  login=bundle.getString(BundleConstant.EXTRA);
  String tag=bundle.getString(BundleConstant.EXTRA_THREE);
  long id=bundle.getLong(BundleConstant.EXTRA_TWO,-1);
  if (!InputHelper.isEmpty(tag)) {
    manageObservable(RestProvider.getRepoService(isEnterprise()).getTagRelease(login,repoId,tag).doOnNext(release -> {
      if (release != null) {
        sendToView(view -> view.onShowDetails(release));
      }
    }
));
  }
 else   if (id > 0) {
    manageObservable(RestProvider.getRepoService(isEnterprise()).getRelease(login,repoId,id).doOnNext(release -> {
      if (release != null) {
        sendToView(view -> view.onShowDetails(release));
      }
    }
));
  }
  if (!InputHelper.isEmpty(login) && !InputHelper.isEmpty(repoId)) {
    onCallApi(1,null);
  }
}
