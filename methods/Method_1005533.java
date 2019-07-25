@Override public void init(Context context,Logger logger){
  this.context=context;
  this.logger=logger;
  LocationProvider current=getCurrentProvider();
  if (current != null) {
    current.init(context,logger);
  }
}
