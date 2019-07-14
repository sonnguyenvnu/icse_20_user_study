public int getTotalMutations(){
  return (additions == null ? 0 : additions.size()) + (deletions == null ? 0 : deletions.size());
}
