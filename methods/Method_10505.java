public static void takePic(Context mContext,final RxCameraView mCameraView){
  try {
    if (mCameraView.isCameraOpened()) {
      RxVibrateTool.vibrateOnce(mContext,150);
      RxToast.normal("????..");
      mCameraView.takePicture();
    }
 else {
      mCameraView.start();
      RxVibrateTool.vibrateOnce(mContext,150);
      RxToast.normal("????..");
      Observable.create(new ObservableOnSubscribe<Integer>(){
        @Override public void subscribe(        @NonNull ObservableEmitter<Integer> e){
          SystemClock.sleep(500);
          e.onNext(1);
        }
      }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>(){
        @Override public void onSubscribe(        @NonNull Disposable d){
          mDisposable=d;
        }
        @Override public void onNext(        @NonNull Integer integer){
switch (integer) {
case 1:
            try {
              mCameraView.takePicture();
            }
 catch (            Exception var2) {
              RxToast.normal("??????");
            }
          break;
default :
        break;
    }
  }
  @Override public void onError(  @NonNull Throwable e){
  }
  @Override public void onComplete(){
  }
}
);
}
}
 catch (Exception var3) {
RxToast.normal("???????");
}
}
