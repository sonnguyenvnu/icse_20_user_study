@Override public void offsetChildCard(Card anchorCard,int offset){
  if (anchorCard == null) {
    return;
  }
  ConcurrentHashMap<Range<Integer>,Card> newChildren=new ConcurrentHashMap<>();
  boolean startOffset=false;
  for (  Map.Entry<Range<Integer>,Card> entry : mChildren.entrySet()) {
    Range<Integer> key=entry.getKey();
    Card child=entry.getValue();
    if (child == anchorCard) {
      Range<Integer> newKey=Range.create(key.getLower().intValue(),key.getUpper().intValue() + offset);
      newChildren.put(newKey,child);
      startOffset=true;
      continue;
    }
    if (startOffset) {
      Range<Integer> newKey=Range.create(key.getLower().intValue() + offset,key.getUpper().intValue() + offset);
      newChildren.put(newKey,child);
    }
 else {
      newChildren.put(key,child);
    }
  }
  mChildren.clear();
  mChildren.putAll(newChildren);
}
