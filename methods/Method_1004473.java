private PullMessageResult merge(List<PullMessageResult> list){
  if (list.size() == 1)   return list.get(0);
  long pullLogOffset=list.get(0).getPullLogOffset();
  List<Buffer> buffers=new ArrayList<>();
  int bufferTotalSize=0;
  int messageNum=0;
  for (  PullMessageResult result : list) {
    bufferTotalSize+=result.getBufferTotalSize();
    messageNum+=result.getMessageNum();
    buffers.addAll(result.getBuffers());
  }
  return new PullMessageResult(pullLogOffset,buffers,bufferTotalSize,messageNum);
}
