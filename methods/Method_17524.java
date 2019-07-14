private void process(LongArrayList events){
  try {
    policy.stats().stopwatch().start();
    for (int i=0; i < events.size(); i++) {
      policy.record(events.getLong(i));
    }
  }
 catch (  Exception e) {
    sender().tell(ERROR,self());
    context().system().log().error(e,"");
  }
 finally {
    policy.stats().stopwatch().stop();
  }
}
