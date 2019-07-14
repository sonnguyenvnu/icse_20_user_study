@Override public void onStartGistView(@NonNull String gistId){
  startActivityForResult(GistActivity.createIntent(getContext(),gistId,isEnterprise()),BundleConstant.REQUEST_CODE);
}
