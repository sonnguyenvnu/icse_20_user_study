public void show(int account,TLRPC.TL_help_appUpdate update){
  pressCount=0;
  appUpdate=update;
  accountNum=account;
  if (update.document instanceof TLRPC.TL_document) {
    fileName=FileLoader.getAttachFileName(update.document);
  }
  if (getVisibility() != VISIBLE) {
    setVisibility(VISIBLE);
  }
  SpannableStringBuilder builder=new SpannableStringBuilder(update.text);
  MessageObject.addEntitiesToText(builder,update.entities,false,0,false,false,false);
  textView.setText(builder);
  if (update.document instanceof TLRPC.TL_document) {
    acceptTextView.setText(LocaleController.getString("Update",R.string.Update).toUpperCase() + String.format(Locale.US," (%1$s)",AndroidUtilities.formatFileSize(update.document.size)));
  }
 else {
    acceptTextView.setText(LocaleController.getString("Update",R.string.Update).toUpperCase());
  }
  NotificationCenter.getInstance(accountNum).addObserver(this,NotificationCenter.fileDidLoad);
  NotificationCenter.getInstance(accountNum).addObserver(this,NotificationCenter.fileDidFailedLoad);
  NotificationCenter.getInstance(accountNum).addObserver(this,NotificationCenter.FileLoadProgressChanged);
}
