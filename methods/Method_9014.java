public void addStickerTab(TLRPC.Chat chat){
  final int position=tabCount++;
  FrameLayout tab=new FrameLayout(getContext());
  tab.setFocusable(true);
  tab.setOnClickListener(v -> delegate.onPageSelected(position));
  tabsContainer.addView(tab);
  tab.setSelected(position == currentPosition);
  BackupImageView imageView=new BackupImageView(getContext());
  imageView.setRoundRadius(AndroidUtilities.dp(15));
  AvatarDrawable avatarDrawable=new AvatarDrawable();
  avatarDrawable.setTextSize(AndroidUtilities.dp(14));
  avatarDrawable.setInfo(chat);
  imageView.setImage(ImageLocation.getForChat(chat,false),"50_50",avatarDrawable,chat);
  imageView.setAspectFit(true);
  tab.addView(imageView,LayoutHelper.createFrame(30,30,Gravity.CENTER));
}
