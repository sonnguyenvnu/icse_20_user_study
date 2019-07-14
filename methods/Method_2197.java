@DoNotStrip private static byte[] getInTempStorageFromOptions(@Nullable final BitmapFactory.Options options){
  if (options != null && options.inTempStorage != null) {
    return options.inTempStorage;
  }
 else {
    return new byte[IN_TEMP_BUFFER_SIZE];
  }
}
