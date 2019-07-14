/** 
 * ???????????
 * @Override protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {<p> } }
 * @see new RxBusResultSubscriber<ImageRadioResultEvent>() {
 */
public static void openMultiSelectImage(Activity context,RxBusResultDisposable<ImageMultipleResultEvent> rxBusResultDisposable){
  RxGalleryFinal.with(context).image().multiple().crop().imageLoader(ImageLoaderType.GLIDE).subscribe(rxBusResultDisposable).openGallery();
}
