@Override public boolean onFragmentCreate(){
  super.onFragmentCreate();
  DataQuery.getInstance(currentAccount).checkFeaturedStickers();
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.featuredStickersDidLoad);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.stickersDidLoad);
  ArrayList<Long> arrayList=DataQuery.getInstance(currentAccount).getUnreadStickerSets();
  if (arrayList != null) {
    unreadStickers=new ArrayList<>(arrayList);
  }
  updateRows();
  return true;
}
