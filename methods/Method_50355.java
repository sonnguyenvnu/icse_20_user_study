private void start(ImageLoaderType imageLoaderType){
switch (imageLoaderType) {
case PICASSO:
case UNIVERSAL:
    Toast.makeText(getApplicationContext(),imageLoaderType + "???Gif",Toast.LENGTH_SHORT).show();
  break;
}
if (with == null) with=RxGalleryFinal.with(this);
with.image().radio().gif(appCompatCheckBox.isChecked()).imageLoader(imageLoaderType).subscribe(new RxBusResultDisposable<ImageRadioResultEvent>(){
@Override protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
  Toast.makeText(getBaseContext(),"????????" + imageRadioResultEvent.getResult().getOriginalPath(),Toast.LENGTH_SHORT).show();
}
}
).openGallery();
}
