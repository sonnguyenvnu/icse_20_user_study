@SuppressWarnings("unchecked") @NonNull public List<Card> findGroups(final Predicate<Card> predicate){
  Preconditions.checkState(mGroupBasicAdapter != null,"Must call bindView() first");
  List<Card> groups=(List<Card>)mGroupBasicAdapter.getGroups();
  if (predicate == null) {
    return groups;
  }
  List<Card> rs=new LinkedList<Card>();
  for (  Card g : groups) {
    if (predicate.isMatch(g)) {
      rs.add(g);
    }
  }
  return rs;
}
