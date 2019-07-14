private Bitmap loadArtworkFromUrl(String artworkUrl,boolean big,boolean tryLoad){
  String name=ImageLoader.getHttpFileName(artworkUrl);
  File path=ImageLoader.getHttpFilePath(artworkUrl,"jpg");
  if (path.exists()) {
    return ImageLoader.loadBitmap(path.getAbsolutePath(),null,big ? 600 : 100,big ? 600 : 100,false);
  }
  if (tryLoad) {
    loadingFilePath=path.getAbsolutePath();
    if (!big) {
      imageReceiver.setImage(artworkUrl,"48_48",null,null,0);
    }
  }
 else {
    loadingFilePath=null;
  }
  return null;
}
