private static boolean areComponentCollectionsEquals(final int level,final Collection c1,final Collection c2){
  if (level < 1) {
    throw new IllegalArgumentException("Level cannot be < 1");
  }
  if (c1 == null && c2 == null) {
    return true;
  }
  if (c1 != null ? (c2 == null || c1.size() != c2.size()) : c2 != null) {
    return false;
  }
  final Iterator i1=c1.iterator();
  final Iterator i2=c2.iterator();
  while (i1.hasNext() && i2.hasNext()) {
    if (level == 1) {
      if (!((Component)i1.next()).isEquivalentTo((Component)i2.next())) {
        return false;
      }
    }
 else {
      if (!areComponentCollectionsEquals(level - 1,(Collection)i1.next(),(Collection)i2.next())) {
        return false;
      }
    }
  }
  return true;
}
