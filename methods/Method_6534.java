public static TLRPC.PhotoSize scaleAndSaveImage(TLRPC.PhotoSize photoSize,Bitmap bitmap,float maxWidth,float maxHeight,int quality,boolean cache){
  return scaleAndSaveImage(photoSize,bitmap,maxWidth,maxHeight,quality,cache,0,0);
}
