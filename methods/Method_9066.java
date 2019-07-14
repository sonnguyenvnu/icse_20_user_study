private void checkDocuments(){
  int previousCount=recentStickers[currentType].size();
  recentStickers[currentType]=DataQuery.getInstance(currentAccount).getRecentStickers(currentType);
  if (stickersGridAdapter != null) {
    stickersGridAdapter.notifyDataSetChanged();
  }
  if (previousCount != recentStickers[currentType].size()) {
    updateStickerTabs();
  }
}
