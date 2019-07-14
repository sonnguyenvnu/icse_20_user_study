@Override public void removeComponents(Card group){
  if (group != null && mCards != null) {
    List<Pair<Range<Integer>,Card>> newCards=new ArrayList<>();
    int cardIdx=findCardIdxForCard(group);
    int removeItemCount=0;
    int removePosition=0;
    for (int i=0, size=mCards.size(); i < size; i++) {
      Pair<Range<Integer>,Card> pair=mCards.get(i);
      int start=pair.first.getLower();
      int end=pair.first.getUpper();
      if (i < cardIdx) {
        newCards.add(pair);
      }
 else       if (i == cardIdx) {
        removePosition=start;
        removeItemCount=end - start;
      }
 else {
        Pair<Range<Integer>,Card> newPair=new Pair<>(Range.create(start - removeItemCount,end - removeItemCount),pair.second);
        newCards.add(newPair);
      }
    }
    group.removed();
    mCards.clear();
    mCards.addAll(newCards);
    mData.removeAll(group.getCells());
    notifyItemRangeRemoved(removePosition,removeItemCount);
    int last=mLayoutManager.findLastVisibleItemPosition();
    notifyItemRangeChanged(removePosition,last - removePosition);
  }
}
