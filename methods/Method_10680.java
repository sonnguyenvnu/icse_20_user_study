/** 
 * ????????
 * @param intent ??
 */
public static void copyIntent(Context context,Intent intent){
  ClipboardManager clipboard=(ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
  clipboard.setPrimaryClip(ClipData.newIntent("intent",intent));
}
