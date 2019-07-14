private static TLRPC.PhotoSize scaleAndSaveImageInternal(TLRPC.PhotoSize photoSize,Bitmap bitmap,int w,int h,float photoW,float photoH,float scaleFactor,int quality,boolean cache,boolean scaleAnyway) throws Exception {
  Bitmap scaledBitmap;
  if (scaleFactor > 1 || scaleAnyway) {
    scaledBitmap=Bitmaps.createScaledBitmap(bitmap,w,h,true);
  }
 else {
    scaledBitmap=bitmap;
  }
  boolean check=photoSize != null;
  TLRPC.TL_fileLocationToBeDeprecated location;
  if (photoSize == null || !(photoSize.location instanceof TLRPC.TL_fileLocationToBeDeprecated)) {
    location=new TLRPC.TL_fileLocationToBeDeprecated();
    location.volume_id=Integer.MIN_VALUE;
    location.dc_id=Integer.MIN_VALUE;
    location.local_id=SharedConfig.getLastLocalId();
    location.file_reference=new byte[0];
    photoSize=new TLRPC.TL_photoSize();
    photoSize.location=location;
    photoSize.w=scaledBitmap.getWidth();
    photoSize.h=scaledBitmap.getHeight();
    if (photoSize.w <= 100 && photoSize.h <= 100) {
      photoSize.type="s";
    }
 else     if (photoSize.w <= 320 && photoSize.h <= 320) {
      photoSize.type="m";
    }
 else     if (photoSize.w <= 800 && photoSize.h <= 800) {
      photoSize.type="x";
    }
 else     if (photoSize.w <= 1280 && photoSize.h <= 1280) {
      photoSize.type="y";
    }
 else {
      photoSize.type="w";
    }
  }
 else {
    location=(TLRPC.TL_fileLocationToBeDeprecated)photoSize.location;
  }
  String fileName=location.volume_id + "_" + location.local_id + ".jpg";
  final File cacheFile=new File(location.volume_id != Integer.MIN_VALUE ? FileLoader.getDirectory(FileLoader.MEDIA_DIR_IMAGE) : FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),fileName);
  FileOutputStream stream=new FileOutputStream(cacheFile);
  scaledBitmap.compress(Bitmap.CompressFormat.JPEG,quality,stream);
  if (cache) {
    ByteArrayOutputStream stream2=new ByteArrayOutputStream();
    scaledBitmap.compress(Bitmap.CompressFormat.JPEG,quality,stream2);
    photoSize.bytes=stream2.toByteArray();
    photoSize.size=photoSize.bytes.length;
    stream2.close();
  }
 else {
    photoSize.size=(int)stream.getChannel().size();
  }
  stream.close();
  if (scaledBitmap != bitmap) {
    scaledBitmap.recycle();
  }
  return photoSize;
}
