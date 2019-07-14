/** 
 * Transform cards to layoutHelpers with correct range and add cells in cards into data
 * @param cards cards will be transformed
 * @param data  list of items that items will be added to
 * @return helpers transformed from cards
 */
@NonNull protected List<LayoutHelper> transformCards(@Nullable List<L> cards,final @NonNull List<C> data,@NonNull List<Pair<Range<Integer>,L>> rangeCards){
  if (cards == null || cards.size() == 0) {
    return new LinkedList<>();
  }
  int lastPos=data.size();
  List<LayoutHelper> helpers=new ArrayList<>(cards.size());
  for (int i=0, size=cards.size(); i < size; i++) {
    L card=cards.get(i);
    if (card == null)     continue;
    final String ctype=getCardStringType(card);
    List<C> items=getItems(card);
    if (items == null) {
      continue;
    }
    data.addAll(items);
    int offset=lastPos;
    lastPos+=items.size();
    rangeCards.add(Pair.create(Range.create(offset,lastPos),card));
    LayoutBinder<L> binder=mCardBinderResolver.create(ctype);
    LayoutHelper helper=binder.getHelper(ctype,card);
    if (helper != null) {
      helper.setItemCount(items.size());
      helpers.add(helper);
    }
  }
  return helpers;
}
