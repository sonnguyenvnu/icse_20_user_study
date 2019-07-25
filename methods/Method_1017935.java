private void pending(ProcessContext context,ProcessSession session,String feedId,List<FlowFile> batch){
  beginInitialization(context,session,feedId,batch,false);
  requeueFlowFiles(session,batch);
}
