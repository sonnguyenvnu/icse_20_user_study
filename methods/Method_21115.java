private void startBrowser(final @NonNull List<Intent> targetIntents){
  if (!targetIntents.isEmpty()) {
    final Intent chooserIntent=Intent.createChooser(targetIntents.remove(0),"");
    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,targetIntents.toArray(new Parcelable[targetIntents.size()]));
    startActivity(chooserIntent);
  }
  finish();
}
