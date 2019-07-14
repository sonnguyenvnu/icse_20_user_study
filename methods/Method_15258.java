/** 
 * ???? 
 * @param context
 * @param value
 */
public static void copyText(Context context,String value){
  if (context == null || StringUtil.isNotEmpty(value,true) == false) {
    Log.e(TAG,"copyText  context == null || StringUtil.isNotEmpty(value, true) == false >> return;");
    return;
  }
  ClipData cD=ClipData.newPlainText("simple text",value);
  ClipboardManager clipboardManager=(ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
  clipboardManager.setPrimaryClip(cD);
  showShortToast(context,"???\n" + value);
}
