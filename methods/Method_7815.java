private void addStickerToResult(TLRPC.Document document,Object parent){
  if (document == null) {
    return;
  }
  String key=document.dc_id + "_" + document.id;
  if (stickersMap != null && stickersMap.containsKey(key)) {
    return;
  }
  if (stickers == null) {
    stickers=new ArrayList<>();
    stickersParents=new ArrayList<>();
    stickersMap=new HashMap<>();
  }
  stickers.add(document);
  stickersParents.add(parent);
  stickersMap.put(key,document);
}
