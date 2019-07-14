/** 
 * ?????????
 * @return RxGalleryFinalApi
 */
public static RxGalleryFinalApi onMultiImageResult(IMultiImageCheckedListener listener){
  RxGalleryListener.getInstance().setMultiImageCheckedListener(listener);
  return mRxApi;
}
