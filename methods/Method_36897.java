@Override public void onPageScrolled(final int position,final float positionOffset,final int positionOffsetPixels,int direction){
  if (!isDisposed()) {
    mObserver.onNext(new ScrollEvent(position,positionOffset,positionOffsetPixels));
  }
}
