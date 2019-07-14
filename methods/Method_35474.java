public static Bitmap parseAlbum(File file){
  MediaMetadataRetriever metadataRetriever=new MediaMetadataRetriever();
  try {
    metadataRetriever.setDataSource(file.getAbsolutePath());
  }
 catch (  IllegalArgumentException e) {
    Log.e(TAG,"parseAlbum: ",e);
  }
  byte[] albumData=metadataRetriever.getEmbeddedPicture();
  if (albumData != null) {
    return BitmapFactory.decodeByteArray(albumData,0,albumData.length);
  }
  return null;
}
