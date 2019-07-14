public static void toCircleWithBorder(Bitmap bitmap,int colorARGB,int borderWidthPx,boolean antiAliased){
  Preconditions.checkNotNull(bitmap);
  nativeToCircleWithBorderFilter(bitmap,colorARGB,borderWidthPx,antiAliased);
}
