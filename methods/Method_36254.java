@SuppressWarnings("MissingPermission") private void saveImage(){
  disposePermissionRequest();
  mPermissionRequest=new RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>(){
    @Override public void accept(    Boolean granted) throws Exception {
      if (granted) {
        mBigImageView.saveImageIntoGallery();
      }
    }
  }
,new Consumer<Throwable>(){
    @Override public void accept(    Throwable throwable) throws Exception {
      throwable.printStackTrace();
    }
  }
);
}
