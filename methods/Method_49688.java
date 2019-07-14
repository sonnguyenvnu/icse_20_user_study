@Override public void showDistance(int distanceInMeters){
  TextView tv=(TextView)findViewById(R.id.distance);
  if (distanceInMeters < 1000) {
    tv.setText(String.format(getResources().getString(R.string.distance_meters),distanceInMeters));
  }
 else   if (distanceInMeters < 3000) {
    double distanceInKm=distanceInMeters / 1000.0;
    tv.setText(String.format(getResources().getString(R.string.distance_few_kilometers),distanceInKm));
  }
 else {
    double distanceInKm=distanceInMeters / 1000.0;
    tv.setText(String.format(getResources().getString(R.string.distance_many_kilometers),distanceInKm));
  }
}
