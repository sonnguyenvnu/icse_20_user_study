@Override public int rmdir(String s){
  ensureNotClosed();
  Path dir=Paths.get(s);
  return applyIfPresent(s,(stat) -> applyIfPresent(dir.getParent().toString(),parentStat -> rmdir(stat,parentStat)));
}
