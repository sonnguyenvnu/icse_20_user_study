public static void addToClipboard(CharSequence str){
  try {
    android.content.ClipboardManager clipboard=(android.content.ClipboardManager)ApplicationLoader.applicationContext.getSystemService(Context.CLIPBOARD_SERVICE);
    android.content.ClipData clip=android.content.ClipData.newPlainText("label",str);
    clipboard.setPrimaryClip(clip);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
