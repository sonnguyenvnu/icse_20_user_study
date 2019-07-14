@NonNull public static Intent makeSendStream(@NonNull Uri stream,@NonNull String type){
  return new Intent(Intent.ACTION_SEND).putExtra(Intent.EXTRA_STREAM,stream).setType(type);
}
