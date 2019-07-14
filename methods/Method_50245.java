/** 
 * ???????????
 */
public RxGalleryFinalApi setVDRadioResultEvent(RxBusResultDisposable<ImageRadioResultEvent> t){
  rxGalleryFinal.video();
  rxGalleryFinal.subscribe(t);
  return mRxApi;
}
