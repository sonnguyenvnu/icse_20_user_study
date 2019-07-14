private void navigate(){
  OpenLocationCode code=mView.getLastFullCode();
  CodeArea codeArea=code.decode();
  Location currentLocation=MainActivity.getMainPresenter().getCurrentLocation();
  float[] results=new float[3];
  Location.distanceBetween(currentLocation.getLatitude(),currentLocation.getLongitude(),codeArea.getCenterLatitude(),codeArea.getCenterLongitude(),results);
  float distance=results[0];
  char navigationMode;
  if (distance <= MAX_WALKING_MODE_DISTANCE) {
    navigationMode='w';
  }
 else {
    navigationMode='d';
  }
  Uri gmmIntentUri=Uri.parse(String.format(Locale.US,"google.navigation:q=%f,%f&mode=%s",codeArea.getCenterLatitude(),codeArea.getCenterLongitude(),navigationMode));
  Intent mapIntent=new Intent(Intent.ACTION_VIEW,gmmIntentUri);
  mView.getContext().startActivity(mapIntent);
}
