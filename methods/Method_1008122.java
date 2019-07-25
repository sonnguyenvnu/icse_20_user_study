@Override protected boolean accept() throws IOException {
  return emitDuplicates || seqAtt.getNumPriorUsesInASequence() < 1;
}
