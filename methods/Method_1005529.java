@Override public void start(OnLocationUpdatedListener listener,LocationParams params,boolean singleUpdate){
  if (singleUpdate) {
    throw new IllegalArgumentException("singleUpdate cannot be set to true");
  }
  locationProvider.start(listener,params,false);
  activityProvider.start(this,ActivityParams.NORMAL);
  this.locationParams=params;
  this.locationUpdatedListener=listener;
}
