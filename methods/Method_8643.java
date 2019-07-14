private void updateStickerTabs(){
  if (stickersTab == null) {
    return;
  }
  recentTabBum=-2;
  favTabBum=-2;
  trendingTabNum=-2;
  stickersTabOffset=0;
  int lastPosition=stickersTab.getCurrentPosition();
  stickersTab.removeTabs();
  ArrayList<Long> unread=DataQuery.getInstance(currentAccount).getUnreadStickerSets();
  boolean hasStickers=false;
  if (trendingGridAdapter != null && trendingGridAdapter.getItemCount() != 0 && !unread.isEmpty()) {
    stickersCounter=stickersTab.addIconTabWithCounter(stickerIcons[2]);
    trendingTabNum=stickersTabOffset;
    stickersTabOffset++;
    stickersCounter.setText(String.format("%d",unread.size()));
  }
  if (!favouriteStickers.isEmpty()) {
    favTabBum=stickersTabOffset;
    stickersTabOffset++;
    stickersTab.addIconTab(stickerIcons[1]).setContentDescription(LocaleController.getString("FavoriteStickers",R.string.FavoriteStickers));
    hasStickers=true;
  }
  if (!recentStickers.isEmpty()) {
    recentTabBum=stickersTabOffset;
    stickersTabOffset++;
    stickersTab.addIconTab(stickerIcons[0]).setContentDescription(LocaleController.getString("RecentStickers",R.string.RecentStickers));
    hasStickers=true;
  }
  stickerSets.clear();
  groupStickerSet=null;
  groupStickerPackPosition=-1;
  groupStickerPackNum=-10;
  ArrayList<TLRPC.TL_messages_stickerSet> packs=DataQuery.getInstance(currentAccount).getStickerSets(DataQuery.TYPE_IMAGE);
  for (int a=0; a < packs.size(); a++) {
    TLRPC.TL_messages_stickerSet pack=packs.get(a);
    if (pack.set.archived || pack.documents == null || pack.documents.isEmpty()) {
      continue;
    }
    stickerSets.add(pack);
    hasStickers=true;
  }
  if (info != null) {
    long hiddenStickerSetId=MessagesController.getEmojiSettings(currentAccount).getLong("group_hide_stickers_" + info.id,-1);
    TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(info.id);
    if (chat == null || info.stickerset == null || !ChatObject.hasAdminRights(chat)) {
      groupStickersHidden=hiddenStickerSetId != -1;
    }
 else     if (info.stickerset != null) {
      groupStickersHidden=hiddenStickerSetId == info.stickerset.id;
    }
    if (info.stickerset != null) {
      TLRPC.TL_messages_stickerSet pack=DataQuery.getInstance(currentAccount).getGroupStickerSetById(info.stickerset);
      if (pack != null && pack.documents != null && !pack.documents.isEmpty() && pack.set != null) {
        TLRPC.TL_messages_stickerSet set=new TLRPC.TL_messages_stickerSet();
        set.documents=pack.documents;
        set.packs=pack.packs;
        set.set=pack.set;
        if (groupStickersHidden) {
          groupStickerPackNum=stickerSets.size();
          stickerSets.add(set);
        }
 else {
          groupStickerPackNum=0;
          stickerSets.add(0,set);
        }
        groupStickerSet=info.can_set_stickers ? set : null;
      }
    }
 else     if (info.can_set_stickers) {
      TLRPC.TL_messages_stickerSet pack=new TLRPC.TL_messages_stickerSet();
      if (groupStickersHidden) {
        groupStickerPackNum=stickerSets.size();
        stickerSets.add(pack);
      }
 else {
        groupStickerPackNum=0;
        stickerSets.add(0,pack);
      }
    }
  }
  for (int a=0; a < stickerSets.size(); a++) {
    if (a == groupStickerPackNum) {
      TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(info.id);
      if (chat == null) {
        stickerSets.remove(0);
        a--;
      }
 else {
        stickersTab.addStickerTab(chat);
        hasStickers=true;
      }
    }
 else {
      TLRPC.TL_messages_stickerSet stickerSet=stickerSets.get(a);
      TLObject thumb;
      TLRPC.Document document=stickerSet.documents.get(0);
      if (stickerSet.set.thumb instanceof TLRPC.TL_photoSize) {
        thumb=stickerSet.set.thumb;
      }
 else {
        thumb=document;
      }
      stickersTab.addStickerTab(thumb,document,stickerSet).setContentDescription(stickerSet.set.title + ", " + LocaleController.getString("AccDescrStickerSet",R.string.AccDescrStickerSet));
      hasStickers=true;
    }
  }
  if (trendingGridAdapter != null && trendingGridAdapter.getItemCount() != 0 && unread.isEmpty()) {
    trendingTabNum=stickersTabOffset + stickerSets.size();
    stickersTab.addIconTab(stickerIcons[2]).setContentDescription(LocaleController.getString("FeaturedStickers",R.string.FeaturedStickers));
  }
  stickersTab.updateTabStyles();
  if (lastPosition != 0) {
    stickersTab.onPageScrolled(lastPosition,lastPosition);
  }
  checkPanels();
  if ((!hasStickers || trendingTabNum == 0 && DataQuery.getInstance(currentAccount).areAllTrendingStickerSetsUnread()) && trendingTabNum >= 0) {
    if (scrolledToTrending == 0) {
      showTrendingTab(true);
      scrolledToTrending=hasStickers ? 2 : 1;
    }
  }
 else   if (scrolledToTrending == 1) {
    showTrendingTab(false);
    checkScroll();
    stickersTab.cancelPositionAnimation();
  }
}
