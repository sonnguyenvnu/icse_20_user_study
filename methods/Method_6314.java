private boolean hasContactsPermission(){
  if (Build.VERSION.SDK_INT >= 23) {
    return ApplicationLoader.applicationContext.checkSelfPermission(android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
  }
  Cursor cursor=null;
  try {
    ContentResolver cr=ApplicationLoader.applicationContext.getContentResolver();
    cursor=cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,projectionPhones,null,null,null);
    if (cursor == null || cursor.getCount() == 0) {
      return false;
    }
  }
 catch (  Throwable e) {
    FileLog.e(e);
  }
 finally {
    try {
      if (cursor != null) {
        cursor.close();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  return true;
}
