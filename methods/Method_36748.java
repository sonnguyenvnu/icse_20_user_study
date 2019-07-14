@Override public void insertComponents(int pos,List<BaseCell> components){
  if (mData != null && mData.size() > 0 && components != null && !components.isEmpty() && pos >= 0) {
    int newItemSize=components.size();
    if (mCards != null) {
      List<Pair<Range<Integer>,Card>> newCards=new ArrayList<>();
      for (int i=0, size=mCards.size(); i < size; i++) {
        Pair<Range<Integer>,Card> pair=mCards.get(i);
        int start=pair.first.getLower();
        int end=pair.first.getUpper();
        if (end < pos) {
          newCards.add(pair);
        }
 else         if (start <= pos && pos < end) {
          Pair<Range<Integer>,Card> newPair=new Pair<>(Range.create(start,end + newItemSize),pair.second);
          newCards.add(newPair);
        }
 else         if (pos <= start) {
          Pair<Range<Integer>,Card> newPair=new Pair<>(Range.create(start + newItemSize,end + newItemSize),pair.second);
          newCards.add(newPair);
        }
      }
      mCards.clear();
      mCards.addAll(newCards);
    }
    for (int i=0, size=components.size(); i < size; i++) {
      BaseCell cell=components.get(i);
      if (cell != null) {
        cell.added();
      }
    }
    for (int i=0; i < newItemSize; i++) {
      if ((pos + i) < mData.size()) {
        mData.add(pos + i,components.get(i));
      }
 else {
        mData.add(components.get(i));
      }
    }
    notifyItemRangeInserted(pos,newItemSize);
  }
}
