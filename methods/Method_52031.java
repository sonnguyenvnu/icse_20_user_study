private void updateContextForAnonymousClass(){
  updateClassContext("" + anonymousCounters.peek().incrementAndGet(),NOTLOCAL_PLACEHOLDER);
}
