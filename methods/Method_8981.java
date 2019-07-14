public void initMiniIcons(){
  if (miniDrawBitmap == null) {
    try {
      miniDrawBitmap=Bitmap.createBitmap(AndroidUtilities.dp(48),AndroidUtilities.dp(48),Bitmap.Config.ARGB_8888);
      miniDrawCanvas=new Canvas(miniDrawBitmap);
    }
 catch (    Throwable ignore) {
    }
  }
}
