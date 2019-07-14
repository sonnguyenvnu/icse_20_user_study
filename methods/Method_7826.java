private void showCopyPopup(String urlFinal){
  if (parentActivity == null) {
    return;
  }
  if (linkSheet != null) {
    linkSheet.dismiss();
    linkSheet=null;
  }
  BottomSheet.Builder builder=new BottomSheet.Builder(parentActivity);
  builder.setUseFullscreen(true);
  builder.setTitle(urlFinal);
  builder.setItems(new CharSequence[]{LocaleController.getString("Open",R.string.Open),LocaleController.getString("Copy",R.string.Copy)},(dialog,which) -> {
    if (parentActivity == null) {
      return;
    }
    if (which == 0) {
      Browser.openUrl(parentActivity,urlFinal);
    }
 else     if (which == 1) {
      String url=urlFinal;
      if (url.startsWith("mailto:")) {
        url=url.substring(7);
      }
 else       if (url.startsWith("tel:")) {
        url=url.substring(4);
      }
      AndroidUtilities.addToClipboard(url);
    }
  }
);
  BottomSheet sheet=builder.create();
  showDialog(sheet);
  for (int a=0; a < 2; a++) {
    sheet.setItemColor(a,getTextColor(),getTextColor());
  }
  sheet.setTitleColor(getGrayTextColor());
  if (selectedColor == 0) {
    sheet.setBackgroundColor(0xffffffff);
  }
 else   if (selectedColor == 1) {
    sheet.setBackgroundColor(0xfff5efdc);
  }
 else   if (selectedColor == 2) {
    sheet.setBackgroundColor(0xff141414);
  }
}
