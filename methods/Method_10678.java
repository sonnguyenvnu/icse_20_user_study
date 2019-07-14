/** 
 * ????????
 * @param text ??
 */
public static void copyText(Context context,CharSequence text){
  ClipboardManager clipboard=(ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
  clipboard.setPrimaryClip(ClipData.newPlainText("text",text));
}
