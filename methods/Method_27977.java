@Override public void onFragmentCreated(@NonNull Bundle bundle){
  repoId=bundle.getString(BundleConstant.ID);
  login=bundle.getString(BundleConstant.EXTRA);
  branch=bundle.getString(BundleConstant.EXTRA_TWO);
  path=bundle.getString(BundleConstant.EXTRA_THREE);
  if (!InputHelper.isEmpty(branch)) {
    getCommitCount(branch);
  }
  if (!InputHelper.isEmpty(login) && !InputHelper.isEmpty(repoId)) {
    onCallApi(1,null);
  }
}
