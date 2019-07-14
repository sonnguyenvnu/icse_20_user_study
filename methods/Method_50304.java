public void decodeBitmapInBackground(@NonNull Context context,@NonNull Uri uri,@Nullable Uri outputUri,BitmapLoadCallback loadCallback){
  int maxBitmapSize=BitmapLoadUtils.calculateMaxBitmapSize(context);
  decodeBitmapInBackground(context,uri,outputUri,maxBitmapSize,maxBitmapSize,loadCallback);
}
