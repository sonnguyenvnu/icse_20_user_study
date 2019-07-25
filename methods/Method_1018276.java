@Override public void process(PipelineContext pipelineContext) throws ArkRuntimeException {
  eventAdminService.sendEvent(new ArkEvent(){
    @Override public String getTopic(){
      return Constants.ARK_EVENT_TOPIC_AFTER_FINISH_STARTUP_STAGE;
    }
  }
);
}
