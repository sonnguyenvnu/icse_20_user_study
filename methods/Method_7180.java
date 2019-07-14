public TLRPC.TL_photo generatePhotoSizes(TLRPC.TL_photo photo,String path,Uri imageUri){
  Bitmap bitmap=ImageLoader.loadBitmap(path,imageUri,AndroidUtilities.getPhotoSize(),AndroidUtilities.getPhotoSize(),true);
  if (bitmap == null) {
    bitmap=ImageLoader.loadBitmap(path,imageUri,800,800,true);
  }
  ArrayList<TLRPC.PhotoSize> sizes=new ArrayList<>();
  TLRPC.PhotoSize size=ImageLoader.scaleAndSaveImage(bitmap,90,90,55,true);
  if (size != null) {
    sizes.add(size);
  }
  size=ImageLoader.scaleAndSaveImage(bitmap,AndroidUtilities.getPhotoSize(),AndroidUtilities.getPhotoSize(),80,false,101,101);
  if (size != null) {
    sizes.add(size);
  }
  if (bitmap != null) {
    bitmap.recycle();
  }
  if (sizes.isEmpty()) {
    return null;
  }
 else {
    UserConfig.getInstance(currentAccount).saveConfig(false);
    if (photo == null) {
      photo=new TLRPC.TL_photo();
    }
    photo.date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
    photo.sizes=sizes;
    photo.file_reference=new byte[0];
    return photo;
  }
}
