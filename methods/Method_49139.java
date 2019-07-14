private void cleanup(){
  if (deleted == null || deleted.isEmpty())   return;
  final Set<InternalRelation> deletedSet=new HashSet<>(deleted);
  deleted=null;
  final List<InternalRelation> newlyAdded=new ArrayList<>(added.size() - deletedSet.size() / 2);
  for (  InternalRelation r : added) {
    if (!deletedSet.contains(r))     newlyAdded.add(r);
  }
  added=newlyAdded;
}
