@Override public void prepare(Callback callback,long positionUs){
  this.callback=callback;
  Collections.addAll(childrenPendingPreparation,periods);
  for (  MediaPeriod period : periods) {
    period.prepare(this,positionUs);
  }
}
