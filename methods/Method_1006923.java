@Override public Chunk<I> provide(final StepContribution contribution) throws Exception {
  final Chunk<I> inputs=new Chunk<>();
  repeatOperations.iterate(new RepeatCallback(){
    @Override public RepeatStatus doInIteration(    final RepeatContext context) throws Exception {
      I item=null;
      Timer.Sample sample=Timer.start(Metrics.globalRegistry);
      String status=BatchMetrics.STATUS_SUCCESS;
      try {
        item=read(contribution,inputs);
      }
 catch (      SkipOverflowException e) {
        status=BatchMetrics.STATUS_FAILURE;
        return RepeatStatus.FINISHED;
      }
 finally {
        stopTimer(sample,contribution.getStepExecution(),status);
      }
      if (item == null) {
        inputs.setEnd();
        return RepeatStatus.FINISHED;
      }
      inputs.add(item);
      contribution.incrementReadCount();
      return RepeatStatus.CONTINUABLE;
    }
  }
);
  return inputs;
}
