private void updateStickerTabs(){
  if (scrollSlidingTabStrip == null) {
    return;
  }
  recentTabBum=-2;
  stickersTabOffset=0;
  int lastPosition=scrollSlidingTabStrip.getCurrentPosition();
  scrollSlidingTabStrip.removeTabs();
  if (currentType == DataQuery.TYPE_IMAGE) {
    Drawable drawable=getContext().getResources().getDrawable(R.drawable.ic_masks_msk1);
    Theme.setDrawableColorByKey(drawable,Theme.key_chat_emojiPanelIcon);
    scrollSlidingTabStrip.addIconTab(drawable);
    stickersEmptyView.setText(LocaleController.getString("NoStickers",R.string.NoStickers));
  }
 else {
    Drawable drawable=getContext().getResources().getDrawable(R.drawable.ic_masks_sticker1);
    Theme.setDrawableColorByKey(drawable,Theme.key_chat_emojiPanelIcon);
    scrollSlidingTabStrip.addIconTab(drawable);
    stickersEmptyView.setText(LocaleController.getString("NoMasks",R.string.NoMasks));
  }
  if (!recentStickers[currentType].isEmpty()) {
    recentTabBum=stickersTabOffset;
    stickersTabOffset++;
    scrollSlidingTabStrip.addIconTab(Theme.createEmojiIconSelectorDrawable(getContext(),R.drawable.ic_masks_recent1,Theme.getColor(Theme.key_chat_emojiPanelMasksIcon),Theme.getColor(Theme.key_chat_emojiPanelMasksIconSelected)));
  }
  stickerSets[currentType].clear();
  ArrayList<TLRPC.TL_messages_stickerSet> packs=DataQuery.getInstance(currentAccount).getStickerSets(currentType);
  for (int a=0; a < packs.size(); a++) {
    TLRPC.TL_messages_stickerSet pack=packs.get(a);
    if (pack.set.archived || pack.documents == null || pack.documents.isEmpty()) {
      continue;
    }
    stickerSets[currentType].add(pack);
  }
  for (int a=0; a < stickerSets[currentType].size(); a++) {
    TLRPC.TL_messages_stickerSet set=stickerSets[currentType].get(a);
    TLRPC.Document document=set.documents.get(0);
    scrollSlidingTabStrip.addStickerTab(document,document,set);
  }
  scrollSlidingTabStrip.updateTabStyles();
  if (lastPosition != 0) {
    scrollSlidingTabStrip.onPageScrolled(lastPosition,lastPosition);
  }
  checkPanels();
}
