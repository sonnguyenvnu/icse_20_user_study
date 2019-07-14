private void consumeFirst() throws TokenStreamException {
  consume();
  CommonHiddenStreamToken p=null;
  while (hideMask.member(LA(1).getType()) || discardMask.member(LA(1).getType()) || copyMask.member(LA(1).getType())) {
    if (copyMask.member(LA(1).getType())) {
      hiddenCopy=partialCloneToken(LA(1));
      if (p != null) {
        p.setHiddenAfter(hiddenCopy);
        hiddenCopy.setHiddenBefore(p);
      }
      lastHiddenToken=hiddenCopy;
      if (firstHidden == null) {
        firstHidden=hiddenCopy;
      }
      break;
    }
 else     if (hideMask.member(LA(1).getType())) {
      if (p != null) {
        p.setHiddenAfter(LA(1));
        LA(1).setHiddenBefore(p);
      }
      p=LA(1);
      lastHiddenToken=p;
      if (firstHidden == null) {
        firstHidden=p;
      }
    }
    consume();
  }
}
