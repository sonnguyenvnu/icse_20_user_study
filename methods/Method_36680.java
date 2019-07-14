@SuppressWarnings("unchecked") @NonNull public <C>List<C> findGroups(final Predicate<C> predicate){
  Preconditions.checkState(mGroupBasicAdapter != null,"Must call bindView() first");
  List<C> groups=(List<C>)mGroupBasicAdapter.getGroups();
  if (predicate == null) {
    return groups;
  }
  List<C> rs=new LinkedList<C>();
  for (  C g : groups) {
    if (predicate.isMatch(g)) {
      rs.add(g);
    }
  }
  return rs;
}
