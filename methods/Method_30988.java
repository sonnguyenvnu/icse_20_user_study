@NonNull public static Intent makeSendText(@NonNull CharSequence text,@Nullable String htmlText){
  Intent intent=new Intent().setAction(Intent.ACTION_SEND).putExtra(Intent.EXTRA_TEXT,text);
  if (htmlText != null) {
    intent.putExtra(IntentCompat.EXTRA_HTML_TEXT,htmlText);
  }
  return intent.setType(MIME_TYPE_TEXT_PLAIN);
}
