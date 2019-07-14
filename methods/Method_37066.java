@Override public void replaceComponent(Card oldGroup,Card newGroup){
  if (mData != null && mCards != null && oldGroup != null && newGroup != null) {
    List<BaseCell> oldComponent=oldGroup.getCells();
    List<BaseCell> newComponent=newGroup.getCells();
    int index=mData.indexOf(oldComponent.get(0));
    if (index >= 0) {
      if (mCards != null) {
        List<Pair<Range<Integer>,Card>> newCards=new ArrayList<>();
        int diff=0;
        for (int i=0, size=mCards.size(); i < size; i++) {
          Pair<Range<Integer>,Card> pair=mCards.get(i);
          int start=pair.first.getLower();
          int end=pair.first.getUpper();
          if (end <= index) {
            newCards.add(pair);
          }
 else           if (start <= index && index < end) {
            diff=newComponent.size() - oldComponent.size();
            Pair<Range<Integer>,Card> newPair=new Pair<>(Range.create(start,end + diff),newGroup);
            newCards.add(newPair);
          }
 else           if (index <= start) {
            Pair<Range<Integer>,Card> newPair=new Pair<>(Range.create(start + diff,end + diff),pair.second);
            newCards.add(newPair);
          }
        }
        mCards.clear();
        mCards.addAll(newCards);
      }
      oldGroup.removed();
      newGroup.added();
      mData.removeAll(oldComponent);
      mData.addAll(index,newComponent);
      int oldSize=oldComponent.size();
      int newSize=newComponent.size();
      notifyItemRangeChanged(index,Math.max(oldSize,newSize));
    }
  }
}
