void handleContentUri(final Uri uri,String subjectFromIntent){
  try {
    String attachmentFileName=null;
    String[] projection=new String[]{OpenableColumns.DISPLAY_NAME};
    try (Cursor c=getContentResolver().query(uri,projection,null,null,null)){
      if (c != null && c.moveToFirst()) {
        final int fileNameColumnId=c.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        if (fileNameColumnId >= 0)         attachmentFileName=c.getString(fileNameColumnId);
      }
    }
     if (attachmentFileName == null)     attachmentFileName=subjectFromIntent;
    InputStream in=getContentResolver().openInputStream(uri);
    promptNameAndSave(in,attachmentFileName);
  }
 catch (  Exception e) {
    showErrorDialogAndQuit("Unable to handle shared content:\n\n" + e.getMessage());
    Log.e("termux","handleContentUri(uri=" + uri + ") failed",e);
  }
}
