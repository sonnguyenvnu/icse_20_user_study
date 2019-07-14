/** 
 * ??????? ? ??????
 * @param mContext  ??
 * @param gpsFrom   ???????
 * @param gpsTo     ???????
 * @param storeName ?????
 */
public static void openGaodeMapToGuide(Context mContext,Gps gpsFrom,Gps gpsTo,String storeName){
  Intent intent=new Intent();
  intent.setAction(Intent.ACTION_VIEW);
  intent.addCategory(Intent.CATEGORY_DEFAULT);
  Gps gps=RxLocationTool.GPS84ToGCJ02(gpsFrom.getLongitude(),gpsFrom.getLatitude());
  Gps gps1=RxLocationTool.GPS84ToGCJ02(gpsTo.getLongitude(),gpsTo.getLatitude());
  String url="androidamap://route?" + "sourceApplication=amap" + "&slat=" + gps.getLatitude() + "&slon=" + gps.getLongitude() + "&dlat=" + gps1.getLatitude() + "&dlon=" + gps1.getLongitude() + "&dname=" + storeName + "&dev=0" + "&t=0";
  Uri uri=Uri.parse(url);
  intent.setData(uri);
  mContext.startActivity(intent);
}
