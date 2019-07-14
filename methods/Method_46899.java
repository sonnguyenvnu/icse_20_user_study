private ArrayList<LayoutElementParcelable> listDocs(){
  ArrayList<LayoutElementParcelable> docs=new ArrayList<>();
  final String[] projection={MediaStore.Files.FileColumns.DATA};
  Cursor cursor=c.getContentResolver().query(MediaStore.Files.getContentUri("external"),projection,null,null,null);
  String[] types=new String[]{".pdf",".xml",".html",".asm",".text/x-asm",".def",".in",".rc",".list",".log",".pl",".prop",".properties",".rc",".doc",".docx",".msg",".odt",".pages",".rtf",".txt",".wpd",".wps"};
  if (cursor == null)   return docs;
 else   if (cursor.getCount() > 0 && cursor.moveToFirst()) {
    do {
      String path=cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
      if (path != null && Arrays.asList(types).contains(path)) {
        HybridFileParcelable strings=RootHelper.generateBaseFile(new File(path),showHiddenFiles);
        if (strings != null) {
          LayoutElementParcelable parcelable=createListParcelables(strings);
          if (parcelable != null)           docs.add(parcelable);
        }
      }
    }
 while (cursor.moveToNext());
  }
  cursor.close();
  Collections.sort(docs,(lhs,rhs) -> -1 * Long.valueOf(lhs.date).compareTo(rhs.date));
  if (docs.size() > 20)   for (int i=docs.size() - 1; i > 20; i--) {
    docs.remove(i);
  }
  return docs;
}
