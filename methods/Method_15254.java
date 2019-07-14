/** 
 * ??? 
 * @param context
 * @param phone
 */
public static void call(Activity context,String phone){
  if (StringUtil.isNotEmpty(phone,true)) {
    Uri uri=Uri.parse("tel:" + phone.trim());
    Intent intent=new Intent(Intent.ACTION_CALL,uri);
    toActivity(context,intent);
    return;
  }
  showShortToast(context,"???????~");
}
