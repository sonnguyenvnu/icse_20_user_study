private static boolean isFileOnSdCard(Context context,File file){
  String sdcardPath=getSdcardPath(context);
  return sdcardPath != null && file.getPath().startsWith(sdcardPath);
}
