private ArrayList<LayoutElementParcelable> listImages(){
  ArrayList<LayoutElementParcelable> images=new ArrayList<>();
  final String[] projection={MediaStore.Images.Media.DATA};
  final Cursor cursor=c.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,projection,null,null,null);
  if (cursor == null)   return images;
 else   if (cursor.getCount() > 0 && cursor.moveToFirst()) {
    do {
      String path=cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
      HybridFileParcelable strings=RootHelper.generateBaseFile(new File(path),showHiddenFiles);
      if (strings != null) {
        LayoutElementParcelable parcelable=createListParcelables(strings);
        if (parcelable != null)         images.add(parcelable);
      }
    }
 while (cursor.moveToNext());
  }
  cursor.close();
  return images;
}
