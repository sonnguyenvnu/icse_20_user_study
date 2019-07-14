/** 
 * ????
 * @param flag ????????
 * @Override protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {<p> } }
 * @see new RxBusResultSubscriber<ImageRadioResultEvent>() {
 */
public static RxGalleryFinalApi openRadioSelectImage(Activity context,RxBusResultDisposable<ImageRadioResultEvent> rxBusResultDisposable,boolean flag){
  getInstance(context);
  if (flag) {
    rxGalleryFinal.image().radio().imageLoader(ImageLoaderType.GLIDE).subscribe(rxBusResultDisposable).openGallery();
  }
 else {
    rxGalleryFinal.image().radio().crop().imageLoader(ImageLoaderType.GLIDE).subscribe(rxBusResultDisposable).openGallery();
  }
  return mRxApi;
}
