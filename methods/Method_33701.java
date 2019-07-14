/** 
 * ??????? ??  manager.setText(null);  ????3Android6.0 ???? ??api?????????? manager.getPrimaryClip()?????3Android6.0 ????
 */
public static void clearClipboard(){
  ClipboardManager manager=(ClipboardManager)CloudReaderApplication.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
  if (manager != null) {
    try {
      manager.setPrimaryClip(manager.getPrimaryClip());
      manager.setText(null);
    }
 catch (    Exception e) {
      DebugUtil.error(e.getMessage());
    }
  }
}
