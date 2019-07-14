@DoNotStrip public static Bitmap hookDecodeStream(InputStream inputStream,Rect outPadding,BitmapFactory.Options opts){
  StaticWebpNativeLoader.ensure();
  inputStream=wrapToMarkSupportedStream(inputStream);
  Bitmap bitmap;
  byte[] header=getWebpHeader(inputStream,opts);
  if (WebpSupportStatus.sIsWebpSupportRequired && isWebpHeader(header,0,HEADER_SIZE)) {
    bitmap=nativeDecodeStream(inputStream,opts,getScaleFromOptions(opts),getInTempStorageFromOptions(opts));
    if (bitmap == null) {
      sendWebpErrorLog("webp_direct_decode_stream");
    }
    setWebpBitmapOptions(bitmap,opts);
    setPaddingDefaultValues(outPadding);
  }
 else {
    bitmap=originalDecodeStream(inputStream,outPadding,opts);
    if (bitmap == null) {
      sendWebpErrorLog("webp_direct_decode_stream_failed_on_no_webp");
    }
  }
  return bitmap;
}
