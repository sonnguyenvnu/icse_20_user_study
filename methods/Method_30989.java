@NonNull public static Intent makeSendImage(@NonNull Uri uri,@Nullable CharSequence text){
  Intent intent=makeSendStream(uri,MIME_TYPE_IMAGE_ANY);
  if (text != null) {
    intent.putExtra(Intent.EXTRA_TEXT,text).putExtra(Intent.EXTRA_TITLE,text).putExtra(Intent.EXTRA_SUBJECT,text).putExtra("Kdescription",text);
  }
  return intent;
}
