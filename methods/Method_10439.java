/** 
 * ?? ?????  ?? ??
 * @param mMapView ????
 * @param mapPoint ??????
 */
public static void locationSelf(MapView mMapView,Point mapPoint){
  if (mapPoint == null) {
    RxToast.normal("GPS?????");
  }
 else {
    mMapView.setViewpointCenterAsync(mapPoint,600);
  }
}
