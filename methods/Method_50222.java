/** 
 * ?????????
 * @return RxGalleryFinalApi
 */
public RxGalleryFinalApi onCropImageResult(IRadioImageCheckedListener listener){
  RxGalleryListener.getInstance().setRadioImageCheckedListener(listener);
  return mRxApi;
}
