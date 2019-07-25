@Override public Range acquire(long capacity){
  int idx=max(0,log2Ceil(capacity) - log2Min);
  ISet<Range> s=ranges.nth(idx);
  if (s.size() == 0) {
    split(idx);
  }
  Range r=null;
  if (s.size() > 0) {
    r=s.nth(s.size() - 1);
    s.remove(r);
  }
  return r;
}
