/** 
 * ?????????? ? ??????
 * @param mContext  ??
 * @param gpsFrom   ????????
 * @param storeName ?????
 */
public static void openBrowserToGuide(Context mContext,Gps gpsFrom,String storeName){
  Gps gps=RxLocationTool.GPS84ToGCJ02(gpsFrom.getLongitude(),gpsFrom.getLatitude());
  String url="http://uri.amap.com/navigation?" + "to=" + gps.getLatitude() + "," + gps.getLongitude() + "," + storeName + "" + "&mode=car" + "&policy=1" + "&src=mypage" + "&coordinate=gaode" + "&callnative=0";
  Uri uri=Uri.parse(url);
  Intent intent=new Intent(Intent.ACTION_VIEW,uri);
  mContext.startActivity(intent);
}
