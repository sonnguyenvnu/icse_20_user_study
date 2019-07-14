public void setData(TLObject object,CharSequence name,CharSequence status,boolean divider){
  if (object == null) {
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
  if (optionsButton != null) {
    boolean visible=delegate.onOptionsButtonCheck(ManageChatUserCell.this,false);
    optionsButton.setVisibility(visible ? VISIBLE : INVISIBLE);
    nameTextView.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,20,(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP,LocaleController.isRTL ? (visible ? 46 : 28) : (68 + namePadding),status == null || status.length() > 0 ? 11.5f : 20.5f,LocaleController.isRTL ? (68 + namePadding) : (visible ? 46 : 28),0));
    statusTextView.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,20,(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP,LocaleController.isRTL ? (visible ? 46 : 28) : (68 + namePadding),34.5f,LocaleController.isRTL ? (68 + namePadding) : (visible ? 46 : 28),0));
  }
  needDivider=divider;
  setWillNotDraw(!needDivider);
  update(0);
}
