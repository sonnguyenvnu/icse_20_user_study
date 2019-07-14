@NonNull public static Intent makeView(@NonNull Uri uri){
  return new Intent(Intent.ACTION_VIEW,uri);
}
