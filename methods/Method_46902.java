private ArrayList<LayoutElementParcelable> listRecentFiles(){
  ArrayList<LayoutElementParcelable> recentFiles=new ArrayList<>();
  final String[] projection={MediaStore.Files.FileColumns.DATA,MediaStore.Files.FileColumns.DATE_MODIFIED};
  Calendar c=Calendar.getInstance();
  c.set(Calendar.DAY_OF_YEAR,c.get(Calendar.DAY_OF_YEAR) - 2);
  Date d=c.getTime();
  Cursor cursor=this.c.getContentResolver().query(MediaStore.Files.getContentUri("external"),projection,null,null,null);
  if (cursor == null)   return recentFiles;
  if (cursor.getCount() > 0 && cursor.moveToFirst()) {
    do {
      String path=cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
      File f=new File(path);
      if (d.compareTo(new Date(f.lastModified())) != 1 && !f.isDirectory()) {
        HybridFileParcelable strings=RootHelper.generateBaseFile(new File(path),showHiddenFiles);
        if (strings != null) {
          LayoutElementParcelable parcelable=createListParcelables(strings);
          if (parcelable != null)           recentFiles.add(parcelable);
        }
      }
    }
 while (cursor.moveToNext());
  }
  cursor.close();
  Collections.sort(recentFiles,(lhs,rhs) -> -1 * Long.valueOf(lhs.date).compareTo(rhs.date));
  if (recentFiles.size() > 20)   for (int i=recentFiles.size() - 1; i > 20; i--) {
    recentFiles.remove(i);
  }
  return recentFiles;
}
