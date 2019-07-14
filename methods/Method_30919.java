@Nullable public static CharSequence readText(@NonNull Context context){
  ClipData clipData=getClipboardManager(context).getPrimaryClip();
  if (clipData == null || clipData.getItemCount() == 0) {
    return null;
  }
  return clipData.getItemAt(0).coerceToText(context);
}
