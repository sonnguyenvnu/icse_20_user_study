/** 
 * ??????? ? ??????
 * @param mContext  ??
 * @param gps       ????????
 * @param storeName ?????
 */
public static void openBaiduMapToGuide(Context mContext,Gps gps,String storeName){
  Intent intent=new Intent();
  Gps gps1=RxLocationTool.GPS84ToGCJ02(gps.getLongitude(),gps.getLatitude());
  Gps gps2=RxLocationTool.GCJ02ToBD09(gps1.getLongitude(),gps1.getLatitude());
  String url="baidumap://map/direction?" + "destination=name:" + storeName + "|latlng:" + gps2.getLatitude() + "," + gps2.getLongitude() + "&mode=driving" + "&sy=3" + "&index=0" + "&target=1";
  Uri uri=Uri.parse(url);
  intent.setData(uri);
  mContext.startActivity(intent);
}
