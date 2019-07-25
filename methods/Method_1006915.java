/** 
 * Build a step that processes items in chunks with the completion policy provided. To extend the step to being fault tolerant, call the  {@link SimpleStepBuilder#faultTolerant()} method on the builder. In most cases you willwant to parameterize your call to this method, to preserve the type safety of your readers and writers, e.g. <pre> new StepBuilder(&quot;step1&quot;).&lt;Order, Ledger&gt; chunk(100).reader(new OrderReader()).writer(new LedgerWriter()) // ... etc. </pre>
 * @param completionPolicy the completion policy to use to control chunk processing
 * @return a {@link SimpleStepBuilder}
 * @param < I > the type of item to be processed as input
 * @param < O > the type of item to be output 
 */
public <I,O>SimpleStepBuilder<I,O> chunk(CompletionPolicy completionPolicy){
  return new SimpleStepBuilder<I,O>(this).chunk(completionPolicy);
}
