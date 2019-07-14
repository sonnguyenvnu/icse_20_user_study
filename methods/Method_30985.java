@NonNull private static Intent makePickFile(@NonNull String mimeType,@Nullable String[] mimeTypes,boolean allowMultiple){
  String action=Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ? Intent.ACTION_OPEN_DOCUMENT : Intent.ACTION_GET_CONTENT;
  Intent intent=new Intent(action).addCategory(Intent.CATEGORY_OPENABLE).setType(mimeType);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    if (mimeTypes != null && mimeTypes.length > 0) {
      intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
    }
  }
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
    if (allowMultiple) {
      intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
    }
  }
  return intent;
}
