/** 
 * ?????????
 */
public static String getClipContent(){
  ClipboardManager manager=(ClipboardManager)CloudReaderApplication.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
  if (manager != null) {
    if (manager.hasPrimaryClip() && manager.getPrimaryClip().getItemCount() > 0) {
      CharSequence addedText=manager.getPrimaryClip().getItemAt(0).getText();
      String addedTextString=String.valueOf(addedText);
      if (!TextUtils.isEmpty(addedTextString)) {
        return StringFormatUtil.formatUrl(String.valueOf(addedText));
      }
    }
  }
  return "";
}
