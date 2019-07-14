@Override public boolean hasModifications(){
  return !addedRelations.isEmpty() || !deletedRelations.isEmpty();
}
