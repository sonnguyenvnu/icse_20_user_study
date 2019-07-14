public static void initAcrMap(final Context mContext,final MapView mMapView,final RxMapScaleView mScaleView){
  ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud5954166547,none,2K0RJAY3FLGP9KB10136");
  mScaleView.setMapView(mMapView);
  mMapView.addMapScaleChangedListener(new MapScaleChangedListener(){
    @Override public void mapScaleChanged(    MapScaleChangedEvent mapScaleChangedEvent){
      mScaleView.refreshScaleView();
    }
  }
);
}
