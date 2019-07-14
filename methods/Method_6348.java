public void reorderStickers(int type,final ArrayList<Long> order){
  Collections.sort(stickerSets[type],(lhs,rhs) -> {
    int index1=order.indexOf(lhs.set.id);
    int index2=order.indexOf(rhs.set.id);
    if (index1 > index2) {
      return 1;
    }
 else     if (index1 < index2) {
      return -1;
    }
    return 0;
  }
);
  loadHash[type]=calcStickersHash(stickerSets[type]);
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.stickersDidLoad,type);
  loadStickers(type,false,true);
}
