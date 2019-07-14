/** 
 * ??uri????
 * @param uri uri
 */
public static void copyUri(Context context,Uri uri){
  ClipboardManager clipboard=(ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
  clipboard.setPrimaryClip(ClipData.newUri(context.getContentResolver(),"uri",uri));
}
