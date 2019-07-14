public static TLRPC.PhotoSize scaleAndSaveImage(Bitmap bitmap,float maxWidth,float maxHeight,int quality,boolean cache){
  return scaleAndSaveImage(null,bitmap,maxWidth,maxHeight,quality,cache,0,0);
}
