protected static final void validate(long seqno){
  if (seqno < 0)   throw new IllegalArgumentException("seqno " + seqno + " cannot be negative");
}
