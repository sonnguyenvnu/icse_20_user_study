public SeqnoList add(long... seqnos){
  if (seqnos != null)   for (  long seqno : seqnos)   add(seqno);
  return this;
}
