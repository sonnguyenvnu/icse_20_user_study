private ArrayList<LayoutElementParcelable> listApks(){
  ArrayList<LayoutElementParcelable> apks=new ArrayList<>();
  final String[] projection={MediaStore.Files.FileColumns.DATA};
  Cursor cursor=c.getContentResolver().query(MediaStore.Files.getContentUri("external"),projection,null,null,null);
  if (cursor == null)   return apks;
 else   if (cursor.getCount() > 0 && cursor.moveToFirst()) {
    do {
      String path=cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
      if (path != null && path.endsWith(".apk")) {
        HybridFileParcelable strings=RootHelper.generateBaseFile(new File(path),showHiddenFiles);
        if (strings != null) {
          LayoutElementParcelable parcelable=createListParcelables(strings);
          if (parcelable != null)           apks.add(parcelable);
        }
      }
    }
 while (cursor.moveToNext());
  }
  cursor.close();
  return apks;
}
