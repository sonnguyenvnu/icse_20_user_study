public void setInvoice(TLRPC.TL_messageMediaInvoice invoice,String botname){
  nameTextView.setText(invoice.title);
  detailTextView.setText(invoice.description);
  detailExTextView.setText(botname);
  int maxPhotoWidth;
  if (AndroidUtilities.isTablet()) {
    maxPhotoWidth=(int)(AndroidUtilities.getMinTabletSide() * 0.7f);
  }
 else {
    maxPhotoWidth=(int)(Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) * 0.7f);
  }
  int width=640;
  int height=360;
  float scale=width / (float)(maxPhotoWidth - AndroidUtilities.dp(2));
  width/=scale;
  height/=scale;
  if (invoice.photo != null && invoice.photo.mime_type.startsWith("image/")) {
    nameTextView.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP,LocaleController.isRTL ? 10 : 123,9,LocaleController.isRTL ? 123 : 10,0));
    detailTextView.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP,LocaleController.isRTL ? 10 : 123,33,LocaleController.isRTL ? 123 : 10,0));
    detailExTextView.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP,LocaleController.isRTL ? 10 : 123,90,LocaleController.isRTL ? 123 : 10,0));
    imageView.setVisibility(VISIBLE);
    String filter=String.format(Locale.US,"%d_%d",width,height);
    imageView.getImageReceiver().setImage(ImageLocation.getForWebFile(WebFile.createWithWebDocument(invoice.photo)),filter,null,null,-1,null,invoice,1);
  }
 else {
    nameTextView.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP,17,9,17,0));
    detailTextView.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP,17,33,17,0));
    detailExTextView.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP,17,90,17,0));
    imageView.setVisibility(GONE);
  }
}
