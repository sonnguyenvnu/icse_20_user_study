private void onSubItemClick(int id){
  if (stickerSet == null) {
    return;
  }
  String stickersUrl="https://" + MessagesController.getInstance(currentAccount).linkPrefix + "/addstickers/" + stickerSet.set.short_name;
  if (id == 1) {
    ShareAlert alert=new ShareAlert(getContext(),null,stickersUrl,false,stickersUrl,false);
    if (parentFragment != null) {
      parentFragment.showDialog(alert);
    }
 else {
      alert.show();
    }
  }
 else   if (id == 2) {
    try {
      AndroidUtilities.addToClipboard(stickersUrl);
      Toast.makeText(ApplicationLoader.applicationContext,LocaleController.getString("LinkCopied",R.string.LinkCopied),Toast.LENGTH_SHORT).show();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
}
