/** 
 * ????????
 * @param content ?????
 */
public static void copy(String content){
  if (!TextUtils.isEmpty(content)) {
    ClipboardManager cmb=(ClipboardManager)CloudReaderApplication.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
    cmb.setText(content.trim());
    ClipData clipData=ClipData.newPlainText(null,content);
    cmb.setPrimaryClip(clipData);
  }
}
