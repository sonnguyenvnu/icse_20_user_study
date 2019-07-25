@Benchmark @Group("tpt") public void offer(OfferCounters counters){
  if (!q.relaxedOffer(ONE)) {
    counters.offersFailed++;
  }
 else {
    counters.offersMade++;
  }
}
