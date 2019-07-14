/** 
 * !!! Do not call this method directly. It's not designed for users.
 * @param component the component to be removed
 */
@Override public void removeComponent(BaseCell component){
  int removePosition=getPositionByItem(component);
  if (mData != null && component != null && removePosition >= 0) {
    if (mCards != null) {
      List<Pair<Range<Integer>,Card>> newCards=new ArrayList<>();
      for (int i=0, size=mCards.size(); i < size; i++) {
        Pair<Range<Integer>,Card> pair=mCards.get(i);
        int start=pair.first.getLower();
        int end=pair.first.getUpper();
        if (end < removePosition) {
          newCards.add(pair);
        }
 else         if (start <= removePosition && removePosition < end) {
          int itemCount=end - start - 1;
          if (itemCount > 0) {
            Pair<Range<Integer>,Card> newPair=new Pair<>(Range.create(start,end - 1),pair.second);
            newCards.add(newPair);
          }
        }
 else         if (removePosition <= start) {
          Pair<Range<Integer>,Card> newPair=new Pair<>(Range.create(start - 1,end - 1),pair.second);
          newCards.add(newPair);
        }
      }
      component.removed();
      mCards.clear();
      mCards.addAll(newCards);
      mData.remove(component);
      notifyItemRemoved(removePosition);
      int last=mLayoutManager.findLastVisibleItemPosition();
      notifyItemRangeChanged(removePosition,last - removePosition);
    }
  }
}
