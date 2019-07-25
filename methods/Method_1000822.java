static void _begain(int level) throws Exception {
  Transaction tn=trans.get();
  if (null == tn) {
    tn=New();
    tn.setLevel(level);
    trans.set(tn);
    count.set(0);
    if (DEBUG)     log.debugf("Start New Transaction id=%d, level=%d",tn.getId(),level);
  }
 else {
    if (DEBUG)     log.debugf("Attach Transaction    id=%d, level=%d",tn.getId(),level);
  }
  int tCount=count.get() + 1;
  count.set(tCount);
}
