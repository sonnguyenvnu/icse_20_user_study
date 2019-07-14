/** 
 * ??????
 */
private void openVideoSelectMultiMethod(int type){
switch (type) {
case 0:
    RxGalleryFinalApi.getInstance(this).setVDMultipleResultEvent(new RxBusResultDisposable<ImageMultipleResultEvent>(){
      @Override protected void onEvent(      ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
        Logger.i("???????");
      }
    }
).open();
  break;
case 1:
RxGalleryFinalApi.getInstance(this).setType(RxGalleryFinalApi.SelectRXType.TYPE_VIDEO,RxGalleryFinalApi.SelectRXType.TYPE_SELECT_MULTI).setVDMultipleResultEvent(new RxBusResultDisposable<ImageMultipleResultEvent>(){
  @Override protected void onEvent(  ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
    Logger.i("???????");
  }
}
).open();
break;
case 2:
RxGalleryFinalApi.openMultiSelectVD(this,new RxBusResultDisposable<ImageMultipleResultEvent>(){
@Override protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
Logger.i("???????");
}
}
);
break;
}
}
