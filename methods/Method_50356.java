/** 
 * ????  or  ?????( ?? onActivityResult())
 */
private void openCrop(){
  if (mRbCropZD.isChecked()) {
    String inputImg="";
    Toast.makeText(MainActivity.this,"??????????‘????’??",Toast.LENGTH_SHORT).show();
  }
 else {
    SimpleRxGalleryFinal.get().init(new SimpleRxGalleryFinal.RxGalleryFinalCropListener(){
      @NonNull @Override public Activity getSimpleActivity(){
        return MainActivity.this;
      }
      @Override public void onCropCancel(){
        Toast.makeText(getSimpleActivity(),"?????",Toast.LENGTH_SHORT).show();
      }
      @Override public void onCropSuccess(      @Nullable Uri uri){
        Toast.makeText(getSimpleActivity(),"?????" + uri,Toast.LENGTH_SHORT).show();
      }
      @Override public void onCropError(      @NonNull String errorMessage){
        Toast.makeText(getSimpleActivity(),errorMessage,Toast.LENGTH_SHORT).show();
      }
    }
).openCamera();
  }
}
