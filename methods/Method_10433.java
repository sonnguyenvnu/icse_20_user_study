/** 
 * initialize ArcGis MapView
 * @param mMapView ????
 */
public static void initAcrMap(final Context mContext,final MapView mMapView,final TextView mTvPlottingScale){
  ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud5954166547,none,2K0RJAY3FLGP9KB10136");
  mMapView.addMapScaleChangedListener(new MapScaleChangedListener(){
    @Override public void mapScaleChanged(    MapScaleChangedEvent mapScaleChangedEvent){
      double s=(int)RxMapTool.screenPixelToMetre(RxImageTool.dp2px(30) * 1.5,mapScaleChangedEvent.getSource().getMapScale(),mContext);
      mTvPlottingScale.setText(RxDataTool.changeDistance(s,false));
    }
  }
);
}
