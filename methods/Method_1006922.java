/** 
 * Simple implementation delegates to the  {@link #doWrite(List)} method andincrements the write count in the contribution. Subclasses can handle more complicated scenarios, e.g.with fault tolerance. If output items are skipped they should be removed from the inputs as well.
 * @param contribution the current step contribution
 * @param inputs the inputs that gave rise to the outputs
 * @param outputs the outputs to write
 * @throws Exception if there is a problem
 */
protected void write(StepContribution contribution,Chunk<I> inputs,Chunk<O> outputs) throws Exception {
  Timer.Sample sample=BatchMetrics.createTimerSample();
  String status=BatchMetrics.STATUS_SUCCESS;
  try {
    doWrite(outputs.getItems());
  }
 catch (  Exception e) {
    inputs.clear();
    status=BatchMetrics.STATUS_FAILURE;
    throw e;
  }
 finally {
    stopTimer(sample,contribution.getStepExecution(),"chunk.write",status,"Chunk writing");
  }
  contribution.incrementWriteCount(outputs.size());
}
