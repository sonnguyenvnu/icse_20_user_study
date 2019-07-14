private void grantPermission(@NonNull Context context,@NonNull Intent intent,String packageName,@NonNull List<Uri> attachments){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
  }
 else {
    for (    Uri uri : attachments) {
      context.grantUriPermission(packageName,uri,Intent.FLAG_GRANT_READ_URI_PERMISSION);
    }
  }
}
