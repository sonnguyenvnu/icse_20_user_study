@Override public Range<Integer> getCardRange(String id){
  if (TextUtils.isEmpty(id)) {
    return Range.create(0,0);
  }
  List<Card> cards=getGroups();
  for (int i=0, size=cards.size(); i < size; i++) {
    Card c=cards.get(i);
    if (id.equals(c.id)) {
      return getCardRange(c);
    }
  }
  return Range.create(0,0);
}
