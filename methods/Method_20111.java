private void onUploadResultIntent(Intent intent){
  mDownloadUrl=intent.getParcelableExtra(MyUploadService.EXTRA_DOWNLOAD_URL);
  mFileUri=intent.getParcelableExtra(MyUploadService.EXTRA_FILE_URI);
  updateUI(mAuth.getCurrentUser());
}
