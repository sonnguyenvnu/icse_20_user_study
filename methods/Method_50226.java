/** 
 * ????:??????
 * @Override protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {<p> } }
 * @see new RxBusResultSubscriber<ImageRadioResultEvent>() {
 */
public static void openRadioSelectImage(Activity context,RxBusResultDisposable rxBusResultDisposable){
  RxGalleryFinal.with(context).image().radio().crop().imageLoader(ImageLoaderType.GLIDE).subscribe(rxBusResultDisposable).openGallery();
}
