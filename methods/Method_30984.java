@NonNull public static Intent makeMediaScan(@NonNull File file){
  return makeMediaScan(Uri.fromFile(file));
}
