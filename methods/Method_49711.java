@Override public void getSuggestions(String code){
  OpenLocationCode currentLocationCode=null;
  if (MainActivity.getMainPresenter().getCurrentLocation() != null) {
    currentLocationCode=OpenLocationCodeUtil.createOpenLocationCode(MainActivity.getMainPresenter().getCurrentLocation().getLatitude(),MainActivity.getMainPresenter().getCurrentLocation().getLongitude());
  }
  OpenLocationCode mapLocationCode=null;
  if (MainActivity.getMainPresenter().getMapCameraPosition() != null) {
    CameraPosition position=MainActivity.getMainPresenter().getMapCameraPosition();
    mapLocationCode=OpenLocationCodeUtil.createOpenLocationCode(position.target.latitude,position.target.longitude);
  }
  List<String> localities=Locality.getAllLocalitiesForSearchDisplay(currentLocationCode,mapLocationCode);
  List<String> suggestions=new ArrayList<>();
  for (  String locality : localities) {
    suggestions.add(code + " " + locality);
  }
  mSourceView.showSuggestions(suggestions);
}
