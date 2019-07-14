/** 
 * Add the stats to the reporter, print if completed, and stop the simulator. 
 */
private void reportStats(PolicyStats stats) throws IOException {
  reporter.add(stats);
  if (--remaining == 0) {
    reporter.print();
    context().stop(self());
    System.out.println("Executed in " + stopwatch);
  }
}
