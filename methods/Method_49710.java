@Override public boolean searchCode(String codeStr){
  if (codeStr.trim().isEmpty()) {
    mSourceView.showEmptyCode();
  }
 else {
    OpenLocationCode code;
    CameraPosition position=MainActivity.getMainPresenter().getMapCameraPosition();
    Location location=MainActivity.getMainPresenter().getCurrentLocation();
    if (position != null) {
      code=OpenLocationCodeUtil.getCodeForSearchString(codeStr.trim(),position.target.latitude,position.target.longitude);
    }
 else     if (location != null) {
      code=OpenLocationCodeUtil.getCodeForSearchString(codeStr.trim(),location.getLatitude(),location.getLongitude());
    }
 else {
      code=OpenLocationCodeUtil.getCodeForSearchString(codeStr.trim(),MyMapView.INITIAL_MAP_LATITUDE,MyMapView.INITIAL_MAP_LONGITUDE);
    }
    if (code != null) {
      for (      SearchContract.TargetView view : mTargetViews) {
        view.showSearchCode(code);
      }
      MainActivity.getMainPresenter().getMapActionsListener().stopUpdateCodeOnDrag();
      return true;
    }
  }
  mSourceView.showInvalidCode();
  return false;
}
