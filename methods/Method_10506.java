public static void initCameraEvent(final Context mContext,final RxCameraView mCameraView,final byte[] data,final String fileDir,final String picName,final double mLongitude,final double mLatitude,final boolean isEconomize,final OnRxCamera onRxCamera){
  onRxCamera.onBefore();
  Observable.create(new ObservableOnSubscribe<Integer>(){
    @Override public void subscribe(    @NonNull final ObservableEmitter<Integer> e){
      File fileParent=new File(fileDir);
      File cacheParent=new File(RxConstants.PICTURE_CACHE_PATH);
      if (!cacheParent.exists()) {
        cacheParent.mkdirs();
      }
      if (!fileParent.exists()) {
        fileParent.mkdirs();
      }
      final File cachefile=new File(cacheParent,picName);
      final File compressFile=new File(fileParent,picName);
      OutputStream os=null;
      try {
        os=new FileOutputStream(cachefile);
        os.write(data);
        os.close();
        RxMagic.with(mContext).load(cachefile).setCompressListener(new OnCompressListener(){
          @Override public void onStart(){
            Log.d("????","????");
          }
          @Override public void onSuccess(          File file){
            if (RxFileTool.copyOrMoveFile(file,compressFile,true)) {
              Log.d("????","????");
              onRxCamera.onSuccessCompress(compressFile);
              if (mLongitude != 0 || mLatitude != 0) {
                RxExifTool.writeLatLonIntoJpeg(compressFile.getAbsolutePath(),mLatitude,mLongitude);
                onRxCamera.onSuccessExif(compressFile);
                e.onNext(2);
              }
 else {
                e.onNext(3);
              }
            }
          }
          @Override public void onError(          Throwable e){
            Log.d("????","????");
          }
        }
).launch();
      }
 catch (      IOException e1) {
        Log.w("onPictureTaken","Cannot write to " + compressFile,e1);
      }
 finally {
        if (os != null) {
          try {
            os.close();
            e.onNext(1);
          }
 catch (          IOException e2) {
          }
        }
      }
    }
  }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>(){
    @Override public void onSubscribe(    @NonNull Disposable d){
      mDisposable=d;
    }
    @Override public void onNext(    @NonNull Integer integer){
switch (integer) {
case 1:
        try {
          if (isEconomize) {
            mCameraView.stop();
          }
        }
 catch (        Exception e) {
        }
      break;
case 2:
    RxToast.normal("????");
  break;
case 3:
RxToast.error("????????");
break;
default :
break;
}
}
@Override public void onError(@NonNull Throwable e){
}
@Override public void onComplete(){
}
}
);
}
