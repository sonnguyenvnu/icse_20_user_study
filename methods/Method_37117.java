@Override public void offsetChildCard(Card anchorCard,int offset){
  if (anchorCard == null) {
    return;
  }
  ArrayMap<Range<Integer>,Card> newChildren=new ArrayMap<>();
  boolean startOffset=false;
  for (int i=0, size=mChildren.size(); i < size; i++) {
    Range<Integer> key=mChildren.keyAt(i);
    Card child=mChildren.valueAt(i);
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
  mChildren.putAll((SimpleArrayMap<? extends Range<Integer>,? extends Card>)newChildren);
}
