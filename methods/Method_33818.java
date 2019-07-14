/** 
 * ????:
 * @param mContext     ???
 * @param mUrl         ??????url
 * @param mTitle       title
 * @param isTitleFixed title????
 */
public static void loadUrl(Context mContext,String mUrl,String mTitle,boolean isTitleFixed){
  if (CheckNetwork.isNetworkConnected(mContext)) {
    Intent intent=new Intent(mContext,WebViewActivity.class);
    intent.putExtra("mUrl",mUrl);
    intent.putExtra("isTitleFix",isTitleFixed);
    intent.putExtra("mTitle",mTitle == null ? "" : mTitle);
    mContext.startActivity(intent);
  }
 else {
    ToastUtil.showToastLong("?????????????????");
  }
}
