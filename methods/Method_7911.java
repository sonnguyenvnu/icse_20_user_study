public void setAccount(int account,boolean check){
  accountNumber=account;
  TLRPC.User user=UserConfig.getInstance(accountNumber).getCurrentUser();
  avatarDrawable.setInfo(user);
  textView.setText(ContactsController.formatName(user.first_name,user.last_name));
  imageView.getImageReceiver().setCurrentAccount(account);
  imageView.setImage(ImageLocation.getForUser(user,false),"50_50",avatarDrawable,user);
  checkImageView.setVisibility(check && account == UserConfig.selectedAccount ? VISIBLE : INVISIBLE);
}
