private void onCreate(@Nullable Intent intent){
  if (intent == null || intent.getAction() == null) {
    finish();
    return;
  }
  if (Intent.ACTION_SEND.equals(intent.getAction())) {
    try {
      String sharedText=intent.getStringExtra(Intent.EXTRA_TEXT);
      if (!InputHelper.isEmpty(sharedText)) {
        Uri uri=Uri.parse(sharedText);
        onUriReceived(uri);
      }
    }
 catch (    ExitException ignored) {
    }
  }
 else   if (intent.getAction().equals(Intent.ACTION_VIEW)) {
    if (intent.getData() != null) {
      onUriReceived(intent.getData());
    }
  }
  finish();
}
