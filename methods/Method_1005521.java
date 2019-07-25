@Override public void init(@NonNull Context context,Logger logger){
  this.context=context;
  this.logger=logger;
  activityStore=new ActivityStore(context);
  if (!shouldStart) {
    this.client=new GoogleApiClient.Builder(context).addApi(ActivityRecognition.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
    client.connect();
  }
 else {
    logger.d("already started");
  }
}
