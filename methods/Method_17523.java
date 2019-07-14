@Override public Receive createReceive(){
  return receiveBuilder().match(LongArrayList.class,this::process).matchEquals(FINISH,msg -> finish()).build();
}
