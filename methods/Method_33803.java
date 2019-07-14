/** 
 * ??????????app
 */
private void handleOtherwise(Activity activity,String url){
  String appPackageName="";
  if (url.contains("alipays")) {
    appPackageName="com.eg.android.AlipayGphone";
  }
 else   if (url.contains("weixin://wap/pay")) {
    appPackageName="com.tencent.mm";
  }
 else   if (url.contains("openapp.jdmobile")) {
    appPackageName="com.jingdong.app.mall";
  }
 else {
    startActivity(url);
  }
  if (BaseTools.isApplicationAvilible(activity,appPackageName)) {
    startActivity(url);
  }
}
