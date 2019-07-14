public void setData(TLObject object,TLRPC.EncryptedChat ec,CharSequence name,CharSequence status,int resId,boolean divider){
  if (object == null && name == null && status == null) {
    currentStatus=null;
    currentName=null;
    currentObject=null;
    nameTextView.setText("");
    statusTextView.setText("");
    avatarImageView.setImageDrawable(null);
    return;
  }
  encryptedChat=ec;
  currentStatus=status;
  currentName=name;
  currentObject=object;
  currentDrawable=resId;
  needDivider=divider;
  setWillNotDraw(!needDivider);
  update(0);
}
