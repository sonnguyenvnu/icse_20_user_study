@Override protected void subscribeActual(Observer<? super ClickExposureCellOp> observer){
  if (mExposureDispose == null) {
    mExposureDispose=new ExposureDispose();
  }
  observer.onSubscribe(mExposureDispose);
  if (!mExposureDispose.isDisposed()) {
    observer.onNext(mRxClickExposureEvent);
  }
}
