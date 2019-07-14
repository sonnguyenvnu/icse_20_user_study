@DoNotStrip public static Bitmap hookDecodeByteArray(byte[] array,int offset,int length,BitmapFactory.Options opts){
  StaticWebpNativeLoader.ensure();
  Bitmap bitmap;
  if (WebpSupportStatus.sIsWebpSupportRequired && isWebpHeader(array,offset,length)) {
    bitmap=nativeDecodeByteArray(array,offset,length,opts,getScaleFromOptions(opts),getInTempStorageFromOptions(opts));
    if (bitmap == null) {
      sendWebpErrorLog("webp_direct_decode_array");
    }
    setWebpBitmapOptions(bitmap,opts);
  }
 else {
    bitmap=originalDecodeByteArray(array,offset,length,opts);
    if (bitmap == null) {
      sendWebpErrorLog("webp_direct_decode_array_failed_on_no_webp");
    }
  }
  return bitmap;
}
