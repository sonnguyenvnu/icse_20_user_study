@Benchmark public void empty(Blackhole bh,AsyncCounters counters) throws Exception {
  counters.incrementCurrentRequests();
  bh.consume(httpClient.get("/empty").aggregate().handle((msg,t) -> {
    counters.decrementCurrentRequests();
    if (t != null) {
      counters.incrementNumFailures();
    }
 else {
      counters.incrementNumSuccesses();
    }
    return null;
  }
));
}
