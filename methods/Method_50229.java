/** 
 * ????:??????
 * @Override protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {<p> } }
 * @see new RxBusResultSubscriber<ImageRadioResultEvent>() {
 */
public static void openRadioSelectVD(Activity context,RxBusResultDisposable<ImageRadioResultEvent> rxBusResultDisposable){
  RxGalleryFinal.with(context).multiple().video().imageLoader(ImageLoaderType.GLIDE).subscribe(rxBusResultDisposable).openGallery();
}
