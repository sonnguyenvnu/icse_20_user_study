public void setLocation(TLRPC.TL_messageMediaVenue location,String icon,boolean divider){
  needDivider=divider;
  nameTextView.setText(location.title);
  addressTextView.setText(location.address);
  imageView.setImage(icon,null,null);
  setWillNotDraw(!divider);
}
