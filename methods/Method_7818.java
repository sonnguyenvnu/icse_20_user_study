public void loadStikersForEmoji(CharSequence emoji,boolean emojiOnly){
  boolean searchEmoji=emoji != null && emoji.length() > 0 && emoji.length() <= 14;
  String originalEmoji=emoji.toString();
  int length=emoji.length();
  for (int a=0; a < length; a++) {
    if (a < length - 1 && (emoji.charAt(a) == 0xD83C && emoji.charAt(a + 1) >= 0xDFFB && emoji.charAt(a + 1) <= 0xDFFF || emoji.charAt(a) == 0x200D && (emoji.charAt(a + 1) == 0x2640 || emoji.charAt(a + 1) == 0x2642))) {
      emoji=TextUtils.concat(emoji.subSequence(0,a),emoji.subSequence(a + 2,emoji.length()));
      length-=2;
      a--;
    }
 else     if (emoji.charAt(a) == 0xfe0f) {
      emoji=TextUtils.concat(emoji.subSequence(0,a),emoji.subSequence(a + 1,emoji.length()));
      length--;
      a--;
    }
  }
  lastSticker=emoji.toString();
  boolean isValidEmoji=searchEmoji && (Emoji.isValidEmoji(originalEmoji) || Emoji.isValidEmoji(lastSticker));
  if (emojiOnly || SharedConfig.suggestStickers == 2 || !isValidEmoji) {
    if (visible && (keywordResults == null || keywordResults.isEmpty())) {
      visible=false;
      delegate.needChangePanelVisibility(false);
      notifyDataSetChanged();
    }
    if (!isValidEmoji) {
      searchEmojiByKeyword();
    }
    return;
  }
  cancelEmojiSearch();
  stickers=null;
  stickersParents=null;
  stickersMap=null;
  if (lastReqId != 0) {
    ConnectionsManager.getInstance(currentAccount).cancelRequest(lastReqId,true);
    lastReqId=0;
  }
  delayLocalResults=false;
  final ArrayList<TLRPC.Document> recentStickers=DataQuery.getInstance(currentAccount).getRecentStickersNoCopy(DataQuery.TYPE_IMAGE);
  final ArrayList<TLRPC.Document> favsStickers=DataQuery.getInstance(currentAccount).getRecentStickersNoCopy(DataQuery.TYPE_FAVE);
  int recentsAdded=0;
  for (int a=0, size=recentStickers.size(); a < size; a++) {
    TLRPC.Document document=recentStickers.get(a);
    if (isValidSticker(document,lastSticker)) {
      addStickerToResult(document,"recent");
      recentsAdded++;
      if (recentsAdded >= 5) {
        break;
      }
    }
  }
  for (int a=0, size=favsStickers.size(); a < size; a++) {
    TLRPC.Document document=favsStickers.get(a);
    if (isValidSticker(document,lastSticker)) {
      addStickerToResult(document,"fav");
    }
  }
  HashMap<String,ArrayList<TLRPC.Document>> allStickers=DataQuery.getInstance(currentAccount).getAllStickers();
  ArrayList<TLRPC.Document> newStickers=allStickers != null ? allStickers.get(lastSticker) : null;
  if (newStickers != null && !newStickers.isEmpty()) {
    ArrayList<TLRPC.Document> arrayList=new ArrayList<>(newStickers);
    if (!recentStickers.isEmpty()) {
      Collections.sort(arrayList,new Comparator<TLRPC.Document>(){
        private int getIndex(        long id){
          for (int a=0; a < favsStickers.size(); a++) {
            if (favsStickers.get(a).id == id) {
              return a + 1000;
            }
          }
          for (int a=0; a < recentStickers.size(); a++) {
            if (recentStickers.get(a).id == id) {
              return a;
            }
          }
          return -1;
        }
        @Override public int compare(        TLRPC.Document lhs,        TLRPC.Document rhs){
          int idx1=getIndex(lhs.id);
          int idx2=getIndex(rhs.id);
          if (idx1 > idx2) {
            return -1;
          }
 else           if (idx1 < idx2) {
            return 1;
          }
          return 0;
        }
      }
);
    }
    addStickersToResult(arrayList,null);
  }
  if (SharedConfig.suggestStickers == 0) {
    searchServerStickers(lastSticker,originalEmoji);
  }
  if (stickers != null && !stickers.isEmpty()) {
    if (SharedConfig.suggestStickers == 0 && stickers.size() < 5) {
      delayLocalResults=true;
      delegate.needChangePanelVisibility(false);
      visible=false;
    }
 else {
      checkStickerFilesExistAndDownload();
      boolean show=stickers != null && !stickers.isEmpty() && stickersToLoad.isEmpty();
      if (show) {
        keywordResults=null;
      }
      delegate.needChangePanelVisibility(show);
      visible=true;
    }
    notifyDataSetChanged();
  }
 else   if (visible) {
    delegate.needChangePanelVisibility(false);
    visible=false;
  }
}
