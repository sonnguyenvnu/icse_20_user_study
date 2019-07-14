/** 
 * ???? Gps gpsFrom = new Gps(); gpsFrom.setLongitude(112.938417); gpsFrom.setLatitude(28.115383); <p> Gps gpsTo = new Gps(); gpsTo.setLongitude(112.526993); gpsTo.setLatitude(27.72926); <p> ????/?? ????
 * @param mContext  ??
 * @param gpsFrom   ???????
 * @param gpsTo     ???????
 * @param storeName ?????
 */
public static void openMap(Context mContext,Gps gpsFrom,Gps gpsTo,String storeName){
  if (RxPackageManagerTool.haveExistPackageName(mContext,RxConstants.GAODE_PACKAGE_NAME)) {
    RxMapTool.openGaodeMapToGuide(mContext,gpsFrom,gpsTo,storeName);
  }
 else   if (RxPackageManagerTool.haveExistPackageName(mContext,RxConstants.BAIDU_PACKAGE_NAME)) {
    RxMapTool.openBaiduMapToGuide(mContext,gpsTo,storeName);
  }
 else {
    RxMapTool.openBrowserToGuide(mContext,gpsTo,storeName);
  }
}
