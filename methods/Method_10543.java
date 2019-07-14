@Override public void setGpsInfo(Location location){
  mTvGps.setText(String.format("??: %s  ??: %s\n??: %s  ??: %s",RxLocationTool.gpsToDegree(location.getLongitude()),RxLocationTool.gpsToDegree(location.getLatitude()),location.getAccuracy(),location.getBearing()));
}
