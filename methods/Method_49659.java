@Override public Direction codeLocationUpdated(double latitude,double longitude,boolean isCurrent){
  OpenLocationCode code=OpenLocationCodeUtil.createOpenLocationCode(latitude,longitude);
  mView.displayCode(code);
  Location currentLocation=MainActivity.getMainPresenter().getCurrentLocation();
  Direction direction;
  if (isCurrent) {
    direction=new Direction(code,null,0,0);
  }
 else   if (currentLocation == null) {
    direction=new Direction(null,code,0,0);
  }
 else {
    direction=DirectionUtil.getDirection(currentLocation,code);
  }
  return direction;
}
