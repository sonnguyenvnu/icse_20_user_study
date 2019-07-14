private void decodeQrCode(){
  if (mBigImageView.getCurrentImageFile() == null) {
    return;
  }
  disposeQrCodeDecode();
  mQrCodeDecode=RxJavaInterop.toV2Observable(RxQrCode.scanFromPicture(mBigImageView.getCurrentImageFile().getAbsolutePath())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Result>(){
    @Override public void accept(    Result result) throws Exception {
      Toast.makeText(LongImageActivity.this,"Found " + result.getText(),Toast.LENGTH_SHORT).show();
    }
  }
,new Consumer<Throwable>(){
    @Override public void accept(    Throwable throwable) throws Exception {
      Toast.makeText(LongImageActivity.this,"Not found",Toast.LENGTH_SHORT).show();
    }
  }
);
}
