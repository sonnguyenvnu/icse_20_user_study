@Override protected void subscribeActual(Observer<? super ClickExposureCellOp> observer){
  if (!Preconditions.checkMainThread(observer)) {
    return;
  }
  if (mListener == null) {
    mListener=new RxClickListener(mRxClickExposureEvent,observer);
  }
 else {
    mListener.setRxClickExposureEvent(mRxClickExposureEvent);
    mListener.setObserver(observer);
  }
  observer.onSubscribe(mListener);
  mRxClickExposureEvent.getArg1().setOnClickListener(mListener);
}
