@NonNull public static Intent makeView(@NonNull Uri uri,@NonNull String type){
  return new Intent(Intent.ACTION_VIEW).setDataAndType(uri,type).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
}
