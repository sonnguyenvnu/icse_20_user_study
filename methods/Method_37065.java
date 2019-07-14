@Override public void replaceComponent(List<BaseCell> oldComponent,List<BaseCell> newComponent){
  if (mData != null && oldComponent != null && newComponent != null && oldComponent.size() > 0 && newComponent.size() > 0) {
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
            Pair<Range<Integer>,Card> newPair=new Pair<>(Range.create(start,end + diff),pair.second);
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
      for (int i=0, size=oldComponent.size(); i < size; i++) {
        BaseCell cell=oldComponent.get(i);
        if (cell != null) {
          cell.removed();
        }
      }
      for (int i=0, size=newComponent.size(); i < size; i++) {
        BaseCell cell=newComponent.get(i);
        if (cell != null) {
          cell.added();
        }
      }
      mData.removeAll(oldComponent);
      mData.addAll(index,newComponent);
      int oldSize=oldComponent.size();
      int newSize=newComponent.size();
      notifyItemRangeChanged(index,Math.max(oldSize,newSize));
    }
  }
}
