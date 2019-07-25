@Override public boolean contains(BibEntry entry){
  return query.isMatch(entry);
}
