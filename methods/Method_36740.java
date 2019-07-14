@NonNull @Override protected List<LayoutHelper> transformCards(@Nullable List<Card> cards,@NonNull List<BaseCell> data,@NonNull List<Pair<Range<Integer>,Card>> rangeCards){
  if (cards == null) {
    return super.transformCards(cards,data,rangeCards);
  }
  for (  Card card : cards) {
    if (!TextUtils.isEmpty(card.id)) {
      mIdCardCache.put(card.id,card);
    }
  }
  List<LayoutHelper> layoutHelpers=super.transformCards(cards,data,rangeCards);
  mIdCardCache.clear();
  return layoutHelpers;
}
