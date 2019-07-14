@Override public void onSuccessSubmission(PullRequest issueModel){
  hideProgress();
  Intent intent=new Intent();
  intent.putExtras(Bundler.start().put(BundleConstant.ITEM,issueModel).end());
  setResult(RESULT_OK,intent);
  finish();
  showMessage(R.string.success,R.string.successfully_submitted);
}
