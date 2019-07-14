public void setCount(int count){
  nameTextView.setText("");
  avatarDrawable.setInfo(0,null,null,false,"+" + LocaleController.formatShortNumber(count,result));
  imageView.setImage(null,"50_50",avatarDrawable,null);
}
