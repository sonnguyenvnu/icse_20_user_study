public void shareMyContact(final MessageObject messageObject){
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  builder.setTitle(LocaleController.getString("ShareYouPhoneNumberTitle",R.string.ShareYouPhoneNumberTitle));
  if (currentUser != null) {
    if (currentUser.bot) {
      builder.setMessage(LocaleController.getString("AreYouSureShareMyContactInfoBot",R.string.AreYouSureShareMyContactInfoBot));
    }
 else {
      builder.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("AreYouSureShareMyContactInfoUser",R.string.AreYouSureShareMyContactInfoUser,PhoneFormat.getInstance().format("+" + UserConfig.getInstance(currentAccount).getCurrentUser().phone),ContactsController.formatName(currentUser.first_name,currentUser.last_name))));
    }
  }
 else {
    builder.setMessage(LocaleController.getString("AreYouSureShareMyContactInfo",R.string.AreYouSureShareMyContactInfo));
  }
  builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> {
    SendMessagesHelper.getInstance(currentAccount).sendMessage(UserConfig.getInstance(currentAccount).getCurrentUser(),dialog_id,messageObject,null,null);
    moveScrollToLastMessage();
    hideFieldPanel(false);
  }
);
  builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
  showDialog(builder.create());
}
