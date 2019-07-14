public void setData(TLObject object,CharSequence name,CharSequence status,int resId){
  if (object == null && name == null && status == null) {
    currrntStatus=null;
    currentName=null;
    currentObject=null;
    nameTextView.setText("");
    statusTextView.setText("");
    avatarImageView.setImageDrawable(null);
    return;
  }
  currrntStatus=status;
  currentName=name;
  currentObject=object;
  currentDrawable=resId;
  update(0);
}
