private int calcFeaturedStickersHash(ArrayList<TLRPC.StickerSetCovered> sets){
  long acc=0;
  for (int a=0; a < sets.size(); a++) {
    TLRPC.StickerSet set=sets.get(a).set;
    if (set.archived) {
      continue;
    }
    int high_id=(int)(set.id >> 32);
    int lower_id=(int)set.id;
    acc=((acc * 20261) + 0x80000000L + high_id) % 0x80000000L;
    acc=((acc * 20261) + 0x80000000L + lower_id) % 0x80000000L;
    if (unreadStickerSets.contains(set.id)) {
      acc=((acc * 20261) + 0x80000000L + 1) % 0x80000000L;
    }
  }
  return (int)acc;
}
