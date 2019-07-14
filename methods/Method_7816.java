private void addStickersToResult(ArrayList<TLRPC.Document> documents,Object parent){
  if (documents == null || documents.isEmpty()) {
    return;
  }
  for (int a=0, size=documents.size(); a < size; a++) {
    TLRPC.Document document=documents.get(a);
    String key=document.dc_id + "_" + document.id;
    if (stickersMap != null && stickersMap.containsKey(key)) {
      continue;
    }
    if (stickers == null) {
      stickers=new ArrayList<>();
      stickersParents=new ArrayList<>();
      stickersMap=new HashMap<>();
    }
    stickers.add(document);
    boolean found=false;
    for (int b=0, size2=document.attributes.size(); b < size2; b++) {
      TLRPC.DocumentAttribute attribute=document.attributes.get(b);
      if (attribute instanceof TLRPC.TL_documentAttributeSticker) {
        stickersParents.add(attribute.stickerset);
        found=true;
        break;
      }
    }
    if (!found) {
      stickersParents.add(parent);
    }
    stickersMap.put(key,document);
  }
}
