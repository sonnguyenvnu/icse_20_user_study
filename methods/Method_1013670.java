@VisibleForTesting protected Pair<Integer,Integer> statistics(List<EventModel> events){
  int success=0, fail=0;
  for (  EventModel event : events) {
    String prop=event.getEventProperty();
    Properties properties=JsonCodec.INSTANCE.decode(prop,Properties.class);
    boolean successOrNot=EmailService.DEFAULT.checkAsyncEmailResult(new EmailResponse(){
      @Override public Properties getProperties(){
        return properties;
      }
    }
);
    if (successOrNot) {
      success++;
    }
 else {
      logger.info("[statistics] email fail sent out: {}",event.getEventProperty());
      fail++;
    }
  }
  return new Pair<>(success,fail);
}
