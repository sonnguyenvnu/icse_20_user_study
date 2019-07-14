/** 
 * ????
 * @param context     ???
 * @param phoneNumber ????
 * @param content     ??
 */
public static void sendSms(Context context,String phoneNumber,String content){
  Uri uri=Uri.parse("smsto:" + (RxDataTool.isNullString(phoneNumber) ? "" : phoneNumber));
  Intent intent=new Intent(Intent.ACTION_SENDTO,uri);
  intent.putExtra("sms_body",RxDataTool.isNullString(content) ? "" : content);
  context.startActivity(intent);
}
