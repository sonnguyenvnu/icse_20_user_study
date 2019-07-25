@Benchmark @Group("tpt") public void offer(OfferCounters counters){
  ChannelProducer<Ping> lProducer=producer;
  if (!lProducer.claim()) {
    counters.offersFailed++;
  }
 else {
    Ping element=lProducer.currentElement();
    element.setValue(writeValue);
    lProducer.commit();
    counters.offersMade++;
  }
  if (DELAY_PRODUCER != 0) {
    Blackhole.consumeCPU(DELAY_PRODUCER);
  }
}
