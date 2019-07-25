/** 
 * Re-write any pages that belong to one of the chunks in the given set.
 * @param set the set of chunk ids
 * @return number of pages actually re-written
 */
final int rewrite(Set<Integer> set){
  if (!singleWriter) {
    return rewrite(getRootPage(),set);
  }
  RootReference rootReference=lockRoot(getRoot(),1);
  int appendCounter=rootReference.getAppendCounter();
  try {
    if (appendCounter > 0) {
      rootReference=flushAppendBuffer(rootReference,true);
      assert rootReference.getAppendCounter() == 0;
    }
    int res=rewrite(rootReference.root,set);
    return res;
  }
  finally {
    unlockRoot();
  }
}
