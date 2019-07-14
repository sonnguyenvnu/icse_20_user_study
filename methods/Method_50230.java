/** 
 * ???? ??????? ???9???
 * @Override protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {<p> } }
 * @see new RxBusResultSubscriber<ImageRadioResultEvent>() {
 */
public static void openMultiSelectVD(Activity context,RxBusResultDisposable<ImageMultipleResultEvent> rxBusResultDisposable){
  RxGalleryFinal.with(context).video().multiple().maxSize(9).imageLoader(ImageLoaderType.UNIVERSAL).subscribe(rxBusResultDisposable).openGallery();
}
