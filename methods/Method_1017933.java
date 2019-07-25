public FlowFile initialize(ProcessContext context,ProcessSession session,FlowFile flowFile){
  return ensureFeedId(context,session,flowFile);
}
