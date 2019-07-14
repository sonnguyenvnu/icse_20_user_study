private void uploadFromUri(Uri fileUri){
  Log.d(TAG,"uploadFromUri:src:" + fileUri.toString());
  mFileUri=fileUri;
  updateUI(mAuth.getCurrentUser());
  mDownloadUrl=null;
  startService(new Intent(this,MyUploadService.class).putExtra(MyUploadService.EXTRA_FILE_URI,fileUri).setAction(MyUploadService.ACTION_UPLOAD));
  showProgressDialog(getString(R.string.progress_uploading));
}
