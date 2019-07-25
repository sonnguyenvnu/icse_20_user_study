@Override public void init(Context context,Logger logger){
  this.locationManager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
  this.logger=logger;
  mContext=context;
  locationStore=new LocationStore(context);
}
