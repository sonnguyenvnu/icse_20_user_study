public ArrayList<TLRPC.Document> getRecentStickers(int type){
  ArrayList<TLRPC.Document> arrayList=recentStickers[type];
  return new ArrayList<>(arrayList.subList(0,Math.min(arrayList.size(),20)));
}
