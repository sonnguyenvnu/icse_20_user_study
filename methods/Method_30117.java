private void shareImage(){
  File file=mAdapter.getFile(mViewPager.getCurrentItem());
  if (file == null) {
    return;
  }
  Activity activity=getActivity();
  Uri uri=FileUtils.getContentUri(file,activity);
  AppUtils.startActivityWithChooser(IntentUtils.makeSendImage(uri,null),activity);
}
