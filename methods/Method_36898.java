@Override public void onPageSelected(final int position){
  if (!isDisposed()) {
    mObserver.onNext(position);
  }
}
