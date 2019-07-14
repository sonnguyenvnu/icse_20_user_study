public static TLRPC.PhotoSize scaleAndSaveImage(Bitmap bitmap,float maxWidth,float maxHeight,int quality,boolean cache,int minWidth,int minHeight){
  return scaleAndSaveImage(null,bitmap,maxWidth,maxHeight,quality,cache,minWidth,minHeight);
}
