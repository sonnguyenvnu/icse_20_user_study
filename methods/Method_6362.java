private static int calcStickersHash(ArrayList<TLRPC.TL_messages_stickerSet> sets){
  long acc=0;
  for (int a=0; a < sets.size(); a++) {
    TLRPC.StickerSet set=sets.get(a).set;
    if (set.archived) {
      continue;
    }
    acc=((acc * 20261) + 0x80000000L + set.hash) % 0x80000000L;
  }
  return (int)acc;
}
