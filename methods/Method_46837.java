/** 
 * Initiates loading of file/uri by getting an input stream associated with it on a worker thread
 */
private void load(){
  Snackbar.make(scrollView,R.string.loading,Snackbar.LENGTH_SHORT).show();
  new ReadFileTask(getContentResolver(),mFile,getExternalCacheDir(),isRootExplorer(),(data) -> {
switch (data.error) {
case ReadFileTask.NORMAL:
      cacheFile=data.cachedFile;
    mOriginal=data.fileContents;
  try {
    mInput.setText(data.fileContents);
    if (mFile.scheme == EditableFileAbstraction.SCHEME_FILE && getExternalCacheDir() != null && mFile.hybridFileParcelable.getPath().contains(getExternalCacheDir().getPath()) && cacheFile == null) {
      mInput.setInputType(EditorInfo.TYPE_NULL);
      mInput.setSingleLine(false);
      mInput.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
      Snackbar snackbar=Snackbar.make(mInput,getResources().getString(R.string.file_read_only),Snackbar.LENGTH_INDEFINITE);
      snackbar.setAction(getResources().getString(R.string.got_it).toUpperCase(),v -> snackbar.dismiss());
      snackbar.show();
    }
    if (data.fileContents.isEmpty()) {
      mInput.setHint(R.string.file_empty);
    }
 else {
      mInput.setHint(null);
    }
  }
 catch (  OutOfMemoryError e) {
    Toast.makeText(getApplicationContext(),R.string.error,Toast.LENGTH_SHORT).show();
    finish();
  }
break;
case ReadFileTask.EXCEPTION_STREAM_NOT_FOUND:
Toast.makeText(getApplicationContext(),R.string.error_file_not_found,Toast.LENGTH_SHORT).show();
finish();
break;
case ReadFileTask.EXCEPTION_IO:
Toast.makeText(getApplicationContext(),R.string.error_io,Toast.LENGTH_SHORT).show();
finish();
break;
}
}
).execute();
}
