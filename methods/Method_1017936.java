private void reinitialize(ProcessContext context,ProcessSession session,String feedId,List<FlowFile> batch){
  beginInitialization(context,session,feedId,batch,true);
  requeueFlowFiles(session,batch);
}
