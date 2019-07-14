@Override public void onLocationChanged(Location location){
  mTvAboutLocation.setText("??: " + RxLocationTool.gpsToDegree(location.getLongitude()) + "\n??: " + RxLocationTool.gpsToDegree(location.getLatitude()) + "\n??: " + location.getAccuracy() + "\n??: " + location.getAltitude() + "\n??: " + location.getBearing() + "\n??: " + location.getSpeed());
}
