/** 
 * ????????
 */
public static RxGalleryFinalApi onCrop(boolean flag){
  if (rxGalleryFinal == null)   return null;
  rxGalleryFinal.crop(flag);
  return mRxApi;
}
