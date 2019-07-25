private void search(LatLng latLng){
  mPb.setVisibility(View.VISIBLE);
  mRvPOI.setVisibility(View.GONE);
  Location location=new Location().lat((float)latLng.getLatitude()).lng((float)latLng.getLongitude());
  Geo2AddressParam geo2AddressParam=new Geo2AddressParam().location(location).get_poi(true);
  mTencentSearch.geo2address(geo2AddressParam,new HttpResponseListener(){
    @Override public void onSuccess(    int arg0,    BaseObject arg1){
      if (isFinishing()) {
        return;
      }
      mPb.setVisibility(View.GONE);
      mRvPOI.setVisibility(View.VISIBLE);
      if (arg1 == null) {
        return;
      }
      mPresenter.loadData((Geo2AddressResultObject)arg1);
    }
    @Override public void onFailure(    int arg0,    String arg1,    Throwable arg2){
      if (isFinishing()) {
        return;
      }
      mPb.setVisibility(View.GONE);
      mRvPOI.setVisibility(View.VISIBLE);
    }
  }
);
}
