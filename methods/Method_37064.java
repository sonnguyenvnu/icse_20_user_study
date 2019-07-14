@Override public void insertBatchComponents(int idx,List<Card> group){
  if (mCards != null && group != null) {
    List<Pair<Range<Integer>,Card>> newCards=new ArrayList<>();
    List<BaseCell> newData=new ArrayList<>();
    int newItemSize=0;
    int lastEnd=0;
    int insertPosition=0;
    if (idx >= 0 && idx < mCards.size()) {
      for (int i=0, size=mCards.size(); i < size; i++) {
        Pair<Range<Integer>,Card> pair=mCards.get(i);
        int start=pair.first.getLower();
        int end=pair.first.getUpper();
        if (i < idx) {
          newCards.add(pair);
          lastEnd=end;
        }
 else         if (i == idx) {
          insertPosition=start;
          for (int j=0, gs=group.size(); j < gs; j++) {
            Card newGroup=group.get(j);
            int childrenSize=newGroup.getCells().size();
            newItemSize+=childrenSize;
            Pair<Range<Integer>,Card> insertPair=new Pair<>(Range.create(lastEnd,lastEnd + childrenSize),newGroup);
            newCards.add(insertPair);
            newData.addAll(newGroup.getCells());
            lastEnd=lastEnd + childrenSize;
          }
          Pair<Range<Integer>,Card> newPair=new Pair<>(Range.create(start + newItemSize,end + newItemSize),pair.second);
          newCards.add(newPair);
          lastEnd=end + newItemSize;
        }
 else {
          Pair<Range<Integer>,Card> newPair=new Pair<>(Range.create(start + newItemSize,end + newItemSize),pair.second);
          newCards.add(newPair);
          lastEnd=end;
        }
      }
    }
 else {
      newCards.addAll(mCards);
      lastEnd=mCards.size() > 0 ? mCards.get(mCards.size() - 1).first.getUpper() : 0;
      insertPosition=lastEnd;
      for (int j=0, gs=group.size(); j < gs; j++) {
        Card newGroup=group.get(j);
        int childrenSize=newGroup.getCells().size();
        newItemSize+=childrenSize;
        Pair<Range<Integer>,Card> insertPair=new Pair<>(Range.create(lastEnd,lastEnd + childrenSize),newGroup);
        newCards.add(insertPair);
        newData.addAll(newGroup.getCells());
        lastEnd=lastEnd + childrenSize;
      }
    }
    for (int i=0, size=group.size(); i < size; i++) {
      Card card=group.get(i);
      if (card != null) {
        card.added();
      }
    }
    mCards.clear();
    mCards.addAll(newCards);
    mData.addAll(insertPosition,newData);
    notifyItemRangeInserted(insertPosition,newItemSize);
  }
}
