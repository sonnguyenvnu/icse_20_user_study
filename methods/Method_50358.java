/** 
 * ?????
 */
private void openMulti(){
  RxGalleryFinal rxGalleryFinal=RxGalleryFinal.with(MainActivity.this).image().multiple();
  if (list != null && !list.isEmpty()) {
    rxGalleryFinal.selected(list);
  }
  rxGalleryFinal.maxSize(8).imageLoader(ImageLoaderType.FRESCO).subscribe(new RxBusResultDisposable<ImageMultipleResultEvent>(){
    @Override protected void onEvent(    ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
      list=imageMultipleResultEvent.getResult();
      Toast.makeText(getBaseContext(),"???" + imageMultipleResultEvent.getResult().size() + "???",Toast.LENGTH_SHORT).show();
    }
    @Override public void onComplete(){
      super.onComplete();
      Toast.makeText(getBaseContext(),"OVER",Toast.LENGTH_SHORT).show();
    }
  }
).openGallery();
}
