private void pruneStack(){
  for (; ; ) {
    Node bottom=headMain.prevMain;
    if ((bottom == headMain) || (bottom.status == Status.MAIN)) {
      break;
    }
 else     if (bottom.status == Status.FILTER) {
      policyStats.recordOperation();
      bottom.removeFrom(StackType.MAIN);
    }
 else     if (bottom.status == Status.NON_RESIDENT) {
      policyStats.recordOperation();
      bottom.removeFrom(StackType.MAIN);
      data.remove(bottom.key);
    }
  }
}
