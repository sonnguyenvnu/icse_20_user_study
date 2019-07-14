@Override public void onFragmentCreated(@NonNull Bundle bundle,@NonNull IssueState issueState){
  repoId=bundle.getString(BundleConstant.ID);
  login=bundle.getString(BundleConstant.EXTRA);
  this.issueState=issueState;
  if (!InputHelper.isEmpty(login) && !InputHelper.isEmpty(repoId)) {
    onCallApi(1,issueState);
  }
}
