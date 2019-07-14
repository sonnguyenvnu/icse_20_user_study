public static void copyText(@Nullable CharSequence label,@NonNull CharSequence text,@NonNull Context context){
  ClipData clipData=ClipData.newPlainText(label,text);
  getClipboardManager(context).setPrimaryClip(clipData);
  showToast(text,context);
}
