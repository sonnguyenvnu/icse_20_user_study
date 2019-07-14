@NonNull public static Intent makeMediaScan(@NonNull Uri uri){
  return new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).setData(uri);
}
