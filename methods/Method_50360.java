/** 
 * OPEN ???????? <p> ???? ??? ?????sample,?????Type???Demo????
 */
private void openImageSelectRadioMethod(int type){
  RxGalleryFinalApi instance=RxGalleryFinalApi.getInstance(MainActivity.this);
switch (type) {
case 0:
    instance.setImageRadioResultEvent(new RxBusResultDisposable<ImageRadioResultEvent>(){
      @Override protected void onEvent(      ImageRadioResultEvent imageRadioResultEvent) throws Exception {
        Logger.i("???????");
      }
    }
).open();
  break;
case 1:
instance.setType(RxGalleryFinalApi.SelectRXType.TYPE_IMAGE,RxGalleryFinalApi.SelectRXType.TYPE_SELECT_RADIO).setImageRadioResultEvent(new RxBusResultDisposable<ImageRadioResultEvent>(){
  @Override protected void onEvent(  ImageRadioResultEvent imageRadioResultEvent) throws Exception {
    Logger.i("???????");
  }
}
).open();
break;
case 2:
RxGalleryFinalApi.openRadioSelectImage(MainActivity.this,new RxBusResultDisposable<ImageRadioResultEvent>(){
@Override protected void onEvent(ImageRadioResultEvent o) throws Exception {
Logger.i("???????");
}
}
,true);
break;
case 3:
instance.openGalleryRadioImgDefault(new RxBusResultDisposable<ImageRadioResultEvent>(){
@Override protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
Logger.i("??????????");
}
}
).onCropImageResult(new IRadioImageCheckedListener(){
@Override public void cropAfter(Object t){
Logger.i("????");
}
@Override public boolean isActivityFinish(){
Logger.i("??false??????true????");
return true;
}
}
);
break;
}
}
