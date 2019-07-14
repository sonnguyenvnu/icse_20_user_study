@Override public void onMessageDialogActionClicked(boolean isOk,@Nullable Bundle bundle){
  super.onMessageDialogActionClicked(isOk,bundle);
  if (isOk && bundle != null) {
    boolean isDownload=bundle.getBoolean(BundleConstant.YES_NO_EXTRA);
    if (isDownload) {
      Uri uri=new Uri.Builder().scheme("https").authority("github.com").appendPath(getPresenter().getLogin()).appendPath(getPresenter().getRepoId()).appendPath("archive").appendPath(ref + ".zip").build();
      RestProvider.downloadFile(getContext(),uri.toString());
    }
  }
}
