public static void copyToClipboard(@NonNull Context context,@NonNull String uri){
  ClipboardManager clipboard=(ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
  ClipData clip=ClipData.newPlainText(context.getString(R.string.app_name),uri);
  if (clipboard != null) {
    clipboard.setPrimaryClip(clip);
    Toasty.success(App.getInstance(),context.getString(R.string.success_copied)).show();
  }
}
