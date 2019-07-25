public void process(MessageBatch batch,boolean oob,boolean internal){
  if (oob)   removeAndDispatchNonBundledMessages(batch);
  tp.submitToThreadPool(new BatchHandler(batch),internal);
}
