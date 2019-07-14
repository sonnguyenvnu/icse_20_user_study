public View addStickerTab(TLObject thumb,TLRPC.Document sticker,Object parentObject){
  final int position=tabCount++;
  FrameLayout tab=new FrameLayout(getContext());
  tab.setTag(thumb);
  tab.setTag(R.id.parent_tag,parentObject);
  tab.setTag(R.id.object_tag,sticker);
  tab.setFocusable(true);
  tab.setOnClickListener(v -> delegate.onPageSelected(position));
  tabsContainer.addView(tab);
  tab.setSelected(position == currentPosition);
  BackupImageView imageView=new BackupImageView(getContext());
  imageView.setAspectFit(true);
  tab.addView(imageView,LayoutHelper.createFrame(30,30,Gravity.CENTER));
  return tab;
}
