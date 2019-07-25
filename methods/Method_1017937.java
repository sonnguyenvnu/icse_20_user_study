private void success(ProcessContext context,ProcessSession session,String feedId,List<FlowFile> batch){
  session.transfer(batch,CommonProperties.REL_SUCCESS);
}
