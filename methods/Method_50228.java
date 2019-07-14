/** 
 * ?????
 * @Override protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {<p> } }
 * @see new RxBusResultSubscriber<ImageRadioResultEvent>() {
 */
public static void openMultiSelectImage(Activity context,int maxSize,RxBusResultDisposable<ImageMultipleResultEvent> rxBusResultDisposable){
  RxGalleryFinal.with(context).image().maxSize(maxSize).multiple().crop().imageLoader(ImageLoaderType.GLIDE).subscribe(rxBusResultDisposable).openGallery();
}
