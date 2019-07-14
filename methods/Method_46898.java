private ArrayList<LayoutElementParcelable> listaudio(){
  String selection=MediaStore.Audio.Media.IS_MUSIC + " != 0";
  String[] projection={MediaStore.Audio.Media.DATA};
  Cursor cursor=c.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection,null,null);
  ArrayList<LayoutElementParcelable> songs=new ArrayList<>();
  if (cursor == null)   return songs;
 else   if (cursor.getCount() > 0 && cursor.moveToFirst()) {
    do {
      String path=cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
      HybridFileParcelable strings=RootHelper.generateBaseFile(new File(path),showHiddenFiles);
      if (strings != null) {
        LayoutElementParcelable parcelable=createListParcelables(strings);
        if (parcelable != null)         songs.add(parcelable);
      }
    }
 while (cursor.moveToNext());
  }
  cursor.close();
  return songs;
}
