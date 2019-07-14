/** 
 * ?????????
 * @param context
 * @param phone
 */
public static void toMessageChat(Activity context,String phone){
  if (context == null || StringUtil.isNotEmpty(phone,true) == false) {
    Log.e(TAG,"sendMessage  context == null || StringUtil.isNotEmpty(phone, true) == false) >> return;");
    return;
  }
  Intent intent=new Intent(Intent.ACTION_VIEW);
  intent.putExtra("address",phone);
  intent.setType("vnd.android-dir/mms-sms");
  toActivity(context,intent);
}
