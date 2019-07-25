protected static int index(long seqno,int length){
  return (int)((seqno) & length - 1);
}
