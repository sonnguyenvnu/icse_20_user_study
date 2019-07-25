@Override public Translog.Operation next() throws IOException {
  for (; index >= 0; index--) {
    final TranslogSnapshot current=translogs[index];
    Translog.Operation op;
    while ((op=current.next()) != null) {
      if (op.seqNo() == SequenceNumbers.UNASSIGNED_SEQ_NO || seenSeqNo.getAndSet(op.seqNo()) == false) {
        return op;
      }
 else {
        overriddenOperations++;
      }
    }
  }
  return null;
}
